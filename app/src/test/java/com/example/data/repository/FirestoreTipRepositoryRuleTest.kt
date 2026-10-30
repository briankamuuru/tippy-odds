package com.example.data.repository

import com.example.base.FirestoreEmulatorTestBase
import com.example.data.firestore.FirestoreTipRepository
import com.example.data.model.TipCategory
import com.example.data.model.TipItem
import com.example.data.model.TipResult
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import java.util.UUID

class FirestoreTipRepositoryRuleTest : FirestoreEmulatorTestBase() {

    @Test
    fun observeAllTips_authenticatedUser_readsTipsSuccessfully() = runBlocking {
        // 1. Seed as Admin
        signInTestUser(ADMIN_EMAIL)
        val adminRepo = FirestoreTipRepository(firestore)
        val testId = "test_tip_${UUID.randomUUID()}"
        val tip = TipItem(
            id = testId,
            homeTeam = "Arsenal",
            awayTeam = "Chelsea",
            league = "Premier League",
            predictionType = "Full Time",
            predictionValue = "Arsenal Win",
            odds = "1.85",
            matchDate = "Oct 10, 2026",
            kickoffTime = "17:30",
            category = TipCategory.FREE,
            resultState = TipResult.PENDING,
            createdAt = System.currentTimeMillis()
        )
        adminRepo.seedInitialTips(listOf(tip))

        // 2. Normal user reads tips
        signInTestUser("regular_user@test.com")
        val userRepo = FirestoreTipRepository(firestore)

        val tips = withTimeout(FLOW_TIMEOUT_MS) {
            userRepo.observeAllTips().first()
        }
        assertTrue(tips.any { it.id == testId })
    }

    @Test
    fun observeAllTips_unauthenticatedUser_failsWithPermissionDenied() = runBlocking {
        // Sign out to test unauthenticated access
        auth.signOut()
        val repository = FirestoreTipRepository(firestore)

        try {
            withTimeout(FLOW_TIMEOUT_MS) {
                repository.observeAllTips().first()
            }
            fail("Expected FirebaseFirestoreException PERMISSION_DENIED")
        } catch (e: Throwable) {
            var current: Throwable? = e
            var firestoreException: FirebaseFirestoreException? = null
            while (current != null) {
                if (current is FirebaseFirestoreException) {
                    firestoreException = current
                    break
                }
                current = current.cause
            }
            assertTrue("Expected PERMISSION_DENIED exception in cause chain, got: $e", firestoreException != null)
            assertEquals(FirebaseFirestoreException.Code.PERMISSION_DENIED, firestoreException?.code)
        }
    }

    @Test
    fun updateTipResult_adminUser_updatesSuccessfully() = runBlocking {
        signInTestUser(ADMIN_EMAIL)
        val adminRepo = FirestoreTipRepository(firestore)
        val testId = "test_update_${UUID.randomUUID()}"
        val tip = TipItem(
            id = testId,
            homeTeam = "Real Madrid",
            awayTeam = "Barcelona",
            league = "La Liga",
            predictionType = "Goals",
            predictionValue = "Over 2.5",
            odds = "1.65",
            matchDate = "Oct 10, 2026",
            kickoffTime = "20:00",
            category = TipCategory.FREE,
            resultState = TipResult.PENDING,
            createdAt = System.currentTimeMillis()
        )
        adminRepo.seedInitialTips(listOf(tip))

        val updated = adminRepo.updateTipResult(testId, TipResult.WON)
        assertTrue(updated)
    }

    private companion object {
        const val ADMIN_EMAIL = "savdatapointsurveys@gmail.com"
        const val FLOW_TIMEOUT_MS = 5000L
    }
}
