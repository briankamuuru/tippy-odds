package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.model.TipCategory
import com.example.data.model.TipItem
import com.example.data.model.TipResult

@Entity(tableName = "tips")
data class TipEntity(
    @PrimaryKey
    val id: String,
    val homeTeam: String,
    val awayTeam: String,
    val league: String,
    val predictionType: String,
    val predictionValue: String,
    val odds: String,
    val matchDate: String,
    val kickoffTime: String,
    val categoryKey: String,
    val resultStateKey: String,
    val createdAt: Long,
    val updatedAt: Long
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
            category = TipCategory.fromKey(categoryKey),
            resultState = TipResult.fromKey(resultStateKey),
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    companion object {
        fun fromDomainModel(item: TipItem): TipEntity {
            return TipEntity(
                id = item.id,
                homeTeam = item.homeTeam,
                awayTeam = item.awayTeam,
                league = item.league,
                predictionType = item.predictionType,
                predictionValue = item.predictionValue,
                odds = item.odds,
                matchDate = item.matchDate,
                kickoffTime = item.kickoffTime,
                categoryKey = item.category.key,
                resultStateKey = item.resultState.key,
                createdAt = item.createdAt,
                updatedAt = item.updatedAt
            )
        }
    }
}
