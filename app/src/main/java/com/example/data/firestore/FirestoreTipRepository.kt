package com.example.data.firestore

import android.content.Context
import com.example.R
import com.example.data.model.TipCategory
import com.example.data.model.TipItem
import com.example.data.model.TipResult
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirestoreTipRepository(
    private val db: FirebaseFirestore
) {
    // Convenience constructor: resolves named database ID strictly from string resources
    constructor(context: Context) : this(
        FirebaseFirestore.getInstance(
            context.applicationContext.getString(R.string.firestore_database_id)
        )
    )

    private val tipsCollection = db.collection("tips")

    // Real-time observation of all tips using snapshots() Flow
    fun observeAllTips(): Flow<List<TipItem>> {
        return tipsCollection
            .snapshots()
            .map { snapshot ->
                snapshot.documents.mapNotNull { doc ->
                    doc.toObject(FirestoreTip::class.java)?.copy(id = doc.id)?.toDomainModel()
                }
            }
            .catch { exception ->
                if (exception !is CancellationException) {
                    handleFirestoreError(exception as Exception, OperationType.LIST, tipsCollection.path)
                }
                throw exception
            }
            .flowOn(Dispatchers.IO)
    }

    // Real-time observation by category and result state
    fun observeTipsByCategoryAndResult(category: TipCategory, resultState: TipResult): Flow<List<TipItem>> {
        return tipsCollection
            .whereEqualTo("category", category.key)
            .whereEqualTo("resultState", resultState.key)
            .snapshots()
            .map { snapshot ->
                snapshot.documents.mapNotNull { doc ->
                    doc.toObject(FirestoreTip::class.java)?.copy(id = doc.id)?.toDomainModel()
                }
            }
            .catch { exception ->
                if (exception !is CancellationException) {
                    handleFirestoreError(exception as Exception, OperationType.LIST, tipsCollection.path)
                }
                throw exception
            }
            .flowOn(Dispatchers.IO)
    }

    // Real-time observation of history tips (WON or LOST)
    fun observeHistoryTipsByCategory(category: TipCategory): Flow<List<TipItem>> {
        return tipsCollection
            .whereEqualTo("category", category.key)
            .whereIn("resultState", listOf(TipResult.WON.key, TipResult.LOST.key))
            .snapshots()
            .map { snapshot ->
                snapshot.documents.mapNotNull { doc ->
                    doc.toObject(FirestoreTip::class.java)?.copy(id = doc.id)?.toDomainModel()
                }
            }
            .catch { exception ->
                if (exception !is CancellationException) {
                    handleFirestoreError(exception as Exception, OperationType.LIST, tipsCollection.path)
                }
                throw exception
            }
            .flowOn(Dispatchers.IO)
    }

    // Convenience methods matching domain model calls
    fun getCurrentFreeTips(): Flow<List<TipItem>> =
        observeTipsByCategoryAndResult(TipCategory.FREE, TipResult.PENDING)

    fun getFreeTipsHistory(): Flow<List<TipItem>> =
        observeHistoryTipsByCategory(TipCategory.FREE)

    fun getActiveVipTips(category: TipCategory): Flow<List<TipItem>> =
        observeTipsByCategoryAndResult(category, TipResult.PENDING)

    fun getVipHistory(category: TipCategory): Flow<List<TipItem>> =
        observeHistoryTipsByCategory(category)

    fun getAllTips(): Flow<List<TipItem>> =
        observeAllTips()

    suspend fun updateTipResult(tipId: String, newResult: TipResult): Boolean = withContext(Dispatchers.IO) {
        val docRef = tipsCollection.document(tipId)
        try {
            docRef.update(
                mapOf(
                    "resultState" to newResult.key,
                    "updatedAt" to FieldValue.serverTimestamp()
                )
            ).await()
            true
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.UPDATE, docRef.path)
            false
        }
    }

    suspend fun seedInitialTips(tips: List<TipItem>) = withContext(Dispatchers.IO) {
        try {
            val batch = db.batch()
            tips.forEach { tip ->
                val docRef = tipsCollection.document(tip.id)
                val data = mapOf(
                    "id" to tip.id,
                    "homeTeam" to tip.homeTeam,
                    "awayTeam" to tip.awayTeam,
                    "league" to tip.league,
                    "predictionType" to tip.predictionType,
                    "predictionValue" to tip.predictionValue,
                    "odds" to tip.odds,
                    "matchDate" to tip.matchDate,
                    "kickoffTime" to tip.kickoffTime,
                    "category" to tip.category.key,
                    "resultState" to tip.resultState.key,
                    "createdAt" to FieldValue.serverTimestamp(),
                    "updatedAt" to FieldValue.serverTimestamp()
                )
                batch.set(docRef, data)
            }
            batch.commit().await()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.WRITE, tipsCollection.path)
        }
    }

    suspend fun resetDatabaseToInitial(tips: List<TipItem>) = withContext(Dispatchers.IO) {
        try {
            val batch = db.batch()
            tips.forEach { tip ->
                val docRef = tipsCollection.document(tip.id)
                val data = mapOf(
                    "id" to tip.id,
                    "homeTeam" to tip.homeTeam,
                    "awayTeam" to tip.awayTeam,
                    "league" to tip.league,
                    "predictionType" to tip.predictionType,
                    "predictionValue" to tip.predictionValue,
                    "odds" to tip.odds,
                    "matchDate" to tip.matchDate,
                    "kickoffTime" to tip.kickoffTime,
                    "category" to tip.category.key,
                    "resultState" to tip.resultState.key,
                    "createdAt" to FieldValue.serverTimestamp(),
                    "updatedAt" to FieldValue.serverTimestamp()
                )
                batch.set(docRef, data)
            }
            batch.commit().await()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.WRITE, tipsCollection.path)
        }
    }
}
