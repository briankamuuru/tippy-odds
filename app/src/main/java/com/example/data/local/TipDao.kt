package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TipDao {

    @Query("SELECT * FROM tips ORDER BY createdAt DESC")
    fun getAllTipsFlow(): Flow<List<TipEntity>>

    @Query("SELECT * FROM tips WHERE categoryKey = :categoryKey AND resultStateKey = :resultStateKey ORDER BY createdAt DESC")
    fun getTipsByCategoryAndResultFlow(categoryKey: String, resultStateKey: String): Flow<List<TipEntity>>

    @Query("SELECT * FROM tips WHERE categoryKey = :categoryKey AND resultStateKey IN ('WON', 'LOST') ORDER BY updatedAt DESC, createdAt DESC")
    fun getHistoryTipsByCategoryFlow(categoryKey: String): Flow<List<TipEntity>>

    @Query("SELECT * FROM tips WHERE id = :id LIMIT 1")
    suspend fun getTipById(id: String): TipEntity?

    @Query("SELECT COUNT(*) FROM tips")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTipsIgnoreDuplicates(tips: List<TipEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateTip(tip: TipEntity)

    @Query("UPDATE tips SET resultStateKey = :resultStateKey, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateTipResult(id: String, resultStateKey: String, updatedAt: Long): Int

    @Query("DELETE FROM tips")
    suspend fun clearAll()
}
