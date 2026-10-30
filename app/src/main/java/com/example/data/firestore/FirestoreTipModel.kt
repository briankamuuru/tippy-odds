package com.example.data.firestore

import com.example.data.model.TipCategory
import com.example.data.model.TipItem
import com.example.data.model.TipResult
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp

@IgnoreExtraProperties
data class FirestoreTip(
    val id: String = "",
    val homeTeam: String = "",
    val awayTeam: String = "",
    val league: String = "",
    val predictionType: String = "",
    val predictionValue: String = "",
    val odds: String = "",
    val matchDate: String = "",
    val kickoffTime: String = "",
    val category: String = "FREE",
    val resultState: String = "PENDING",
    @ServerTimestamp val createdAt: Timestamp? = null,
    @ServerTimestamp val updatedAt: Timestamp? = null
) {
    fun toDomainModel(): TipItem {
        return TipItem(
            id = id,
            homeTeam = homeTeam,
            awayTeam = awayTeam,
            league = league,
            predictionType = predictionType,
            predictionValue = predictionValue,
            odds = odds,
            matchDate = matchDate,
            kickoffTime = kickoffTime,
            category = TipCategory.fromKey(category),
            resultState = TipResult.fromKey(resultState),
            createdAt = createdAt?.toDate()?.time ?: System.currentTimeMillis(),
            updatedAt = updatedAt?.toDate()?.time ?: System.currentTimeMillis()
        )
    }

    companion object {
        fun fromDomainModel(item: TipItem): FirestoreTip {
            return FirestoreTip(
                id = item.id,
                homeTeam = item.homeTeam,
                awayTeam = item.awayTeam,
                league = item.league,
                predictionType = item.predictionType,
                predictionValue = item.predictionValue,
                odds = item.odds,
                matchDate = item.matchDate,
                kickoffTime = item.kickoffTime,
                category = item.category.key,
                resultState = item.resultState.key,
                createdAt = Timestamp(item.createdAt / 1000, 0),
                updatedAt = Timestamp(item.updatedAt / 1000, 0)
            )
        }
    }
}
