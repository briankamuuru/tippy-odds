package com.example.data.repository

import com.example.data.local.InitialTipData
import com.example.data.local.TipDao
import com.example.data.local.TipEntity
import com.example.data.model.TipCategory
import com.example.data.model.TipItem
import com.example.data.model.TipResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class TipRepository(
    private val tipDao: TipDao
) {

    suspend fun initializeIfEmpty() = withContext(Dispatchers.IO) {
        val count = tipDao.getCount()
        if (count == 0) {
            val initial = InitialTipData.getInitialTips().map { TipEntity.fromDomainModel(it) }
            tipDao.insertTipsIgnoreDuplicates(initial)
        }
    }

    fun getAllTips(): Flow<List<TipItem>> {
        return tipDao.getAllTipsFlow().map { list -> list.map { it.toDomainModel() } }
    }

    // Free Tips: Current (Pending)
    fun getCurrentFreeTips(): Flow<List<TipItem>> {
        return tipDao.getTipsByCategoryAndResultFlow(
            categoryKey = TipCategory.FREE.key,
            resultStateKey = TipResult.PENDING.key
        ).map { list -> list.map { it.toDomainModel() } }
    }

    // Free Tips: History (Won or Lost)
    fun getFreeTipsHistory(): Flow<List<TipItem>> {
        return tipDao.getHistoryTipsByCategoryFlow(
            categoryKey = TipCategory.FREE.key
        ).map { list -> list.map { it.toDomainModel() } }
    }

    // VIP Active (Pending) for specific VIP category
    fun getActiveVipTips(category: TipCategory): Flow<List<TipItem>> {
        return tipDao.getTipsByCategoryAndResultFlow(
            categoryKey = category.key,
            resultStateKey = TipResult.PENDING.key
        ).map { list -> list.map { it.toDomainModel() } }
    }

    // VIP History for specific VIP category (Independent history routing)
    fun getVipHistory(category: TipCategory): Flow<List<TipItem>> {
        return tipDao.getHistoryTipsByCategoryFlow(
            categoryKey = category.key
        ).map { list -> list.map { it.toDomainModel() } }
    }

    /**
     * Declares or updates a result for a tip.
     * Idempotent: Repeated calls with the same result have the same outcome.
     * Moving to WON or LOST automatically routes it out of active lists and into
     * its category-specific history list without duplicating or altering match details.
     */
    suspend fun updateTipResult(tipId: String, newResult: TipResult): Boolean = withContext(Dispatchers.IO) {
        val rowsAffected = tipDao.updateTipResult(
            id = tipId,
            resultStateKey = newResult.key,
            updatedAt = System.currentTimeMillis()
        )
        rowsAffected > 0
    }

    suspend fun resetDatabaseToInitial() = withContext(Dispatchers.IO) {
        tipDao.clearAll()
        val initial = InitialTipData.getInitialTips().map { TipEntity.fromDomainModel(it) }
        tipDao.insertTipsIgnoreDuplicates(initial)
    }
}
