package com.example.data.model

enum class TipCategory(val key: String) {
    FREE("FREE"),
    VIP_2_PLUS("VIP_2_PLUS"),
    VIP_5_PLUS("VIP_5_PLUS"),
    VIP_10_PLUS("VIP_10_PLUS"),
    VIP_CORRECT_SCORE("VIP_CORRECT_SCORE");

    companion object {
        fun fromKey(key: String): TipCategory {
            return entries.firstOrNull { it.key == key } ?: FREE
        }
    }
}

enum class TipResult(val key: String) {
    PENDING("PENDING"),
    WON("WON"),
    LOST("LOST");

    val isCompleted: Boolean
        get() = this == WON || this == LOST

    companion object {
        fun fromKey(key: String): TipResult {
            return entries.firstOrNull { it.key == key } ?: PENDING
        }
    }
}

data class TipItem(
    val id: String,
    val homeTeam: String,
    val awayTeam: String,
    val league: String,
    val predictionType: String,
    val predictionValue: String,
    val odds: String,
    val matchDate: String,
    val kickoffTime: String,
    val category: TipCategory,
    val resultState: TipResult,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
