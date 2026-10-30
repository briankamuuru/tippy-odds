package com.example

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.AppDatabase
import com.example.data.local.TipDao
import com.example.data.local.TipEntity
import com.example.data.model.TipCategory
import com.example.data.model.TipItem
import com.example.data.model.TipResult
import com.example.data.repository.TipRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class TipRepositoryTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: TipDao
    private lateinit var repository: TipRepository

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.tipDao()
        repository = TipRepository(dao)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testPendingTipTransitionsToWonAndMovesToHistory() = runBlocking {
        val pendingTip = TipEntity(
            id = "test_free_1",
            homeTeam = "Arsenal",
            awayTeam = "Chelsea",
            league = "Premier League",
            predictionType = "Full Time",
            predictionValue = "Arsenal Win",
            odds = "1.85",
            matchDate = "Oct 10, 2026",
            kickoffTime = "17:30",
            categoryKey = TipCategory.FREE.key,
            resultStateKey = TipResult.PENDING.key,
            createdAt = 1000L,
            updatedAt = 1000L
        )
        dao.insertOrUpdateTip(pendingTip)

        // 1. Initial State: Must be in Current Free Tips, not in History
        val initialCurrent = repository.getCurrentFreeTips().first()
        val initialHistory = repository.getFreeTipsHistory().first()
        assertEquals(1, initialCurrent.size)
        assertEquals(0, initialHistory.size)
        assertEquals(TipResult.PENDING, initialCurrent[0].resultState)

        // 2. Transition: Declare WON
        val updated = repository.updateTipResult("test_free_1", TipResult.WON)
        assertTrue(updated)

        // 3. Post-Transition State: Removed from Current, present in History
        val updatedCurrent = repository.getCurrentFreeTips().first()
        val updatedHistory = repository.getFreeTipsHistory().first()
        assertEquals(0, updatedCurrent.size)
        assertEquals(1, updatedHistory.size)
        assertEquals(TipResult.WON, updatedHistory[0].resultState)
        assertEquals("1.85", updatedHistory[0].odds)
        assertEquals("Arsenal", updatedHistory[0].homeTeam)
    }

    @Test
    fun testPendingTipTransitionsToLostAndMovesToHistory() = runBlocking {
        val pendingTip = TipEntity(
            id = "test_free_2",
            homeTeam = "Real Madrid",
            awayTeam = "Barcelona",
            league = "La Liga",
            predictionType = "Total Goals",
            predictionValue = "Over 2.5",
            odds = "1.65",
            matchDate = "Oct 10, 2026",
            kickoffTime = "20:00",
            categoryKey = TipCategory.FREE.key,
            resultStateKey = TipResult.PENDING.key,
            createdAt = 1000L,
            updatedAt = 1000L
        )
        dao.insertOrUpdateTip(pendingTip)

        // Declare LOST
        repository.updateTipResult("test_free_2", TipResult.LOST)

        val updatedCurrent = repository.getCurrentFreeTips().first()
        val updatedHistory = repository.getFreeTipsHistory().first()
        assertEquals(0, updatedCurrent.size)
        assertEquals(1, updatedHistory.size)
        assertEquals(TipResult.LOST, updatedHistory[0].resultState)
        assertTrue(updatedHistory[0].resultState.isCompleted)
    }

    @Test
    fun testIdempotentResultUpdatesDoNotDuplicateHistory() = runBlocking {
        val tip = TipEntity(
            id = "test_idempotent",
            homeTeam = "Liverpool",
            awayTeam = "Everton",
            league = "Premier League",
            predictionType = "Full Time",
            predictionValue = "Liverpool Win",
            odds = "1.50",
            matchDate = "Oct 10, 2026",
            kickoffTime = "12:30",
            categoryKey = TipCategory.FREE.key,
            resultStateKey = TipResult.PENDING.key,
            createdAt = 1000L,
            updatedAt = 1000L
        )
        dao.insertOrUpdateTip(tip)

        // Update to WON multiple times
        repository.updateTipResult("test_idempotent", TipResult.WON)
        repository.updateTipResult("test_idempotent", TipResult.WON)
        repository.updateTipResult("test_idempotent", TipResult.WON)

        val history = repository.getFreeTipsHistory().first()
        assertEquals(1, history.size)
        assertEquals("test_idempotent", history[0].id)
        assertEquals(TipResult.WON, history[0].resultState)
    }

    @Test
    fun testDuplicateInsertionPrevention() = runBlocking {
        val tip1 = TipEntity(
            id = "dup_test_1",
            homeTeam = "Bayern",
            awayTeam = "Dortmund",
            league = "Bundesliga",
            predictionType = "Goals",
            predictionValue = "Over 3.5",
            odds = "2.10",
            matchDate = "Oct 10, 2026",
            kickoffTime = "17:30",
            categoryKey = TipCategory.VIP_2_PLUS.key,
            resultStateKey = TipResult.PENDING.key,
            createdAt = 1000L,
            updatedAt = 1000L
        )

        // Try inserting the same entity twice
        dao.insertTipsIgnoreDuplicates(listOf(tip1, tip1))

        assertEquals(1, dao.getCount())
    }

    @Test
    fun testIndependentVipHistoryRouting() = runBlocking {
        // Insert completed tips across different categories
        val tip2Plus = TipEntity(
            id = "vip_2p_1",
            homeTeam = "Inter",
            awayTeam = "Milan",
            league = "Serie A",
            predictionType = "Win",
            predictionValue = "Inter Win",
            odds = "2.20",
            matchDate = "Oct 08, 2026",
            kickoffTime = "19:45",
            categoryKey = TipCategory.VIP_2_PLUS.key,
            resultStateKey = TipResult.WON.key,
            createdAt = 1000L,
            updatedAt = 2000L
        )
        val tip5Plus = TipEntity(
            id = "vip_5p_1",
            homeTeam = "Newcastle",
            awayTeam = "Brighton",
            league = "Premier League",
            predictionType = "Combo",
            predictionValue = "Draw & BTTS",
            odds = "5.20",
            matchDate = "Oct 08, 2026",
            kickoffTime = "14:00",
            categoryKey = TipCategory.VIP_5_PLUS.key,
            resultStateKey = TipResult.LOST.key,
            createdAt = 1000L,
            updatedAt = 2000L
        )
        val tipCorrectScore = TipEntity(
            id = "vip_cs_1",
            homeTeam = "PSG",
            awayTeam = "Monaco",
            league = "Ligue 1",
            predictionType = "Score",
            predictionValue = "2 - 1",
            odds = "8.50",
            matchDate = "Oct 08, 2026",
            kickoffTime = "20:00",
            categoryKey = TipCategory.VIP_CORRECT_SCORE.key,
            resultStateKey = TipResult.WON.key,
            createdAt = 1000L,
            updatedAt = 2000L
        )

        dao.insertTipsIgnoreDuplicates(listOf(tip2Plus, tip5Plus, tipCorrectScore))

        // Verify independent history routing:
        // 2+ History must ONLY contain 2+ tips
        val history2Plus = repository.getVipHistory(TipCategory.VIP_2_PLUS).first()
        assertEquals(1, history2Plus.size)
        assertEquals("vip_2p_1", history2Plus[0].id)

        // 5+ History must ONLY contain 5+ tips
        val history5Plus = repository.getVipHistory(TipCategory.VIP_5_PLUS).first()
        assertEquals(1, history5Plus.size)
        assertEquals("vip_5p_1", history5Plus[0].id)

        // Correct Score History must ONLY contain Correct Score tips
        val historyCS = repository.getVipHistory(TipCategory.VIP_CORRECT_SCORE).first()
        assertEquals(1, historyCS.size)
        assertEquals("vip_cs_1", historyCS[0].id)

        // 10+ History must be empty
        val history10Plus = repository.getVipHistory(TipCategory.VIP_10_PLUS).first()
        assertEquals(0, history10Plus.size)
    }
}
