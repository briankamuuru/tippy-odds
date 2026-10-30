package com.example.data.local

import com.example.data.model.TipCategory
import com.example.data.model.TipItem
import com.example.data.model.TipResult

object InitialTipData {

    fun getInitialTips(): List<TipItem> {
        val now = System.currentTimeMillis()
        var offset = 0L

        fun nextTime(): Long {
            offset += 1000L
            return now + offset
        }

        return listOf(
            // ==================== FREE TIPS - CURRENT (PENDING) ====================
            TipItem(
                id = "free_curr_1",
                homeTeam = "Arsenal",
                awayTeam = "Chelsea",
                league = "Premier League",
                predictionType = "Full Time Result",
                predictionValue = "Arsenal Win (1)",
                odds = "1.85",
                matchDate = "Oct 10, 2026",
                kickoffTime = "17:30",
                category = TipCategory.FREE,
                resultState = TipResult.PENDING,
                createdAt = nextTime()
            ),
            TipItem(
                id = "free_curr_2",
                homeTeam = "Real Madrid",
                awayTeam = "Villarreal",
                league = "La Liga",
                predictionType = "Total Goals",
                predictionValue = "Over 2.5 Goals",
                odds = "1.65",
                matchDate = "Oct 10, 2026",
                kickoffTime = "20:00",
                category = TipCategory.FREE,
                resultState = TipResult.PENDING,
                createdAt = nextTime()
            ),
            TipItem(
                id = "free_curr_3",
                homeTeam = "Inter Milan",
                awayTeam = "Roma",
                league = "Serie A",
                predictionType = "Both Teams to Score",
                predictionValue = "GG / BTTS Yes",
                odds = "1.75",
                matchDate = "Oct 11, 2026",
                kickoffTime = "19:45",
                category = TipCategory.FREE,
                resultState = TipResult.PENDING,
                createdAt = nextTime()
            ),
            TipItem(
                id = "free_curr_4",
                homeTeam = "Bayern Munich",
                awayTeam = "Stuttgart",
                league = "Bundesliga",
                predictionType = "Double Chance",
                predictionValue = "Bayern Munich or Draw (1X)",
                odds = "1.30",
                matchDate = "Oct 11, 2026",
                kickoffTime = "14:30",
                category = TipCategory.FREE,
                resultState = TipResult.PENDING,
                createdAt = nextTime()
            ),
            TipItem(
                id = "free_curr_5",
                homeTeam = "Paris Saint-Germain",
                awayTeam = "Marseille",
                league = "Ligue 1",
                predictionType = "Full Time Result",
                predictionValue = "PSG Win (1)",
                odds = "1.55",
                matchDate = "Oct 11, 2026",
                kickoffTime = "20:45",
                category = TipCategory.FREE,
                resultState = TipResult.PENDING,
                createdAt = nextTime()
            ),

            // ==================== FREE TIPS - HISTORY ====================
            TipItem(
                id = "free_hist_1",
                homeTeam = "Liverpool",
                awayTeam = "Everton",
                league = "Premier League",
                predictionType = "Full Time Result",
                predictionValue = "Liverpool Win (1)",
                odds = "1.52",
                matchDate = "Oct 09, 2026",
                kickoffTime = "19:00",
                category = TipCategory.FREE,
                resultState = TipResult.WON,
                createdAt = nextTime()
            ),
            TipItem(
                id = "free_hist_2",
                homeTeam = "Barcelona",
                awayTeam = "Sevilla",
                league = "La Liga",
                predictionType = "Total Goals",
                predictionValue = "Over 2.5 Goals",
                odds = "1.70",
                matchDate = "Oct 09, 2026",
                kickoffTime = "20:00",
                category = TipCategory.FREE,
                resultState = TipResult.WON,
                createdAt = nextTime()
            ),
            TipItem(
                id = "free_hist_3",
                homeTeam = "Juventus",
                awayTeam = "Lazio",
                league = "Serie A",
                predictionType = "Under / Over",
                predictionValue = "Under 2.5 Goals",
                odds = "1.80",
                matchDate = "Oct 08, 2026",
                kickoffTime = "17:00",
                category = TipCategory.FREE,
                resultState = TipResult.LOST,
                createdAt = nextTime()
            ),
            TipItem(
                id = "free_hist_4",
                homeTeam = "Bayer Leverkusen",
                awayTeam = "Eintracht Frankfurt",
                league = "Bundesliga",
                predictionType = "Both Teams to Score",
                predictionValue = "GG / BTTS Yes",
                odds = "1.60",
                matchDate = "Oct 08, 2026",
                kickoffTime = "14:30",
                category = TipCategory.FREE,
                resultState = TipResult.WON,
                createdAt = nextTime()
            ),

            // ==================== 2+ TIPS VIP - CURRENT (PENDING) ====================
            TipItem(
                id = "vip_2p_curr_1",
                homeTeam = "Manchester City",
                awayTeam = "Tottenham",
                league = "Premier League",
                predictionType = "Combo Prediction",
                predictionValue = "Man City Win & Over 2.5",
                odds = "2.15",
                matchDate = "Oct 10, 2026",
                kickoffTime = "16:30",
                category = TipCategory.VIP_2_PLUS,
                resultState = TipResult.PENDING,
                createdAt = nextTime()
            ),
            TipItem(
                id = "vip_2p_curr_2",
                homeTeam = "Atletico Madrid",
                awayTeam = "Real Sociedad",
                league = "La Liga",
                predictionType = "Double Chance & Goals",
                predictionValue = "1X & Under 3.5 Goals",
                odds = "2.25",
                matchDate = "Oct 10, 2026",
                kickoffTime = "20:00",
                category = TipCategory.VIP_2_PLUS,
                resultState = TipResult.PENDING,
                createdAt = nextTime()
            ),
            TipItem(
                id = "vip_2p_curr_3",
                homeTeam = "AC Milan",
                awayTeam = "Napoli",
                league = "Serie A",
                predictionType = "Both Halves",
                predictionValue = "Milan To Score in Both Halves",
                odds = "2.40",
                matchDate = "Oct 11, 2026",
                kickoffTime = "19:45",
                category = TipCategory.VIP_2_PLUS,
                resultState = TipResult.PENDING,
                createdAt = nextTime()
            ),

            // ==================== 2+ TIPS VIP - HISTORY ====================
            TipItem(
                id = "vip_2p_hist_1",
                homeTeam = "Aston Villa",
                awayTeam = "Wolves",
                league = "Premier League",
                predictionType = "Handicap",
                predictionValue = "Aston Villa -1 Asian Handicap",
                odds = "2.10",
                matchDate = "Oct 09, 2026",
                kickoffTime = "14:00",
                category = TipCategory.VIP_2_PLUS,
                resultState = TipResult.WON,
                createdAt = nextTime()
            ),
            TipItem(
                id = "vip_2p_hist_2",
                homeTeam = "Borussia Dortmund",
                awayTeam = "RB Leipzig",
                league = "Bundesliga",
                predictionType = "Combo Prediction",
                predictionValue = "Dortmund Win & GG",
                odds = "2.65",
                matchDate = "Oct 08, 2026",
                kickoffTime = "17:30",
                category = TipCategory.VIP_2_PLUS,
                resultState = TipResult.LOST,
                createdAt = nextTime()
            ),
            TipItem(
                id = "vip_2p_hist_3",
                homeTeam = "Atalanta",
                awayTeam = "Fiorentina",
                league = "Serie A",
                predictionType = "Total Goals",
                predictionValue = "Over 3.5 Goals",
                odds = "2.35",
                matchDate = "Oct 07, 2026",
                kickoffTime = "19:45",
                category = TipCategory.VIP_2_PLUS,
                resultState = TipResult.WON,
                createdAt = nextTime()
            ),

            // ==================== 5+ TIPS VIP - CURRENT (PENDING) ====================
            TipItem(
                id = "vip_5p_curr_1",
                homeTeam = "Newcastle",
                awayTeam = "Brighton",
                league = "Premier League",
                predictionType = "Multi-Selection Combo",
                predictionValue = "Draw & Both Teams To Score",
                odds = "5.20",
                matchDate = "Oct 10, 2026",
                kickoffTime = "14:00",
                category = TipCategory.VIP_5_PLUS,
                resultState = TipResult.PENDING,
                createdAt = nextTime()
            ),
            TipItem(
                id = "vip_5p_curr_2",
                homeTeam = "Valencia",
                awayTeam = "Athletic Bilbao",
                league = "La Liga",
                predictionType = "Half-Time / Full-Time",
                predictionValue = "Draw / Athletic Bilbao (X/2)",
                odds = "5.50",
                matchDate = "Oct 11, 2026",
                kickoffTime = "15:15",
                category = TipCategory.VIP_5_PLUS,
                resultState = TipResult.PENDING,
                createdAt = nextTime()
            ),

            // ==================== 5+ TIPS VIP - HISTORY ====================
            TipItem(
                id = "vip_5p_hist_1",
                homeTeam = "Porto",
                awayTeam = "Benfica",
                league = "Primeira Liga",
                predictionType = "Half-Time / Full-Time",
                predictionValue = "Draw / Porto (X/1)",
                odds = "5.40",
                matchDate = "Oct 09, 2026",
                kickoffTime = "20:15",
                category = TipCategory.VIP_5_PLUS,
                resultState = TipResult.WON,
                createdAt = nextTime()
            ),
            TipItem(
                id = "vip_5p_hist_2",
                homeTeam = "Lazio",
                awayTeam = "Torino",
                league = "Serie A",
                predictionType = "Scorecast / Goals",
                predictionValue = "Lazio Win & Under 2.5",
                odds = "5.10",
                matchDate = "Oct 07, 2026",
                kickoffTime = "19:45",
                category = TipCategory.VIP_5_PLUS,
                resultState = TipResult.LOST,
                createdAt = nextTime()
            ),

            // ==================== 10+ TIPS VIP - CURRENT (PENDING) ====================
            TipItem(
                id = "vip_10p_curr_1",
                homeTeam = "European Super Ticket",
                awayTeam = "4-Fold Accumulator",
                league = "Multi-League Combo",
                predictionType = "Accumulator",
                predictionValue = "Arsenal + Real Madrid + Bayern + PSG Wins",
                odds = "10.45",
                matchDate = "Oct 10, 2026",
                kickoffTime = "17:30",
                category = TipCategory.VIP_10_PLUS,
                resultState = TipResult.PENDING,
                createdAt = nextTime()
            ),
            TipItem(
                id = "vip_10p_curr_2",
                homeTeam = "Goals Weekend Mega Combo",
                awayTeam = "5-Match Parlay",
                league = "European Leagues",
                predictionType = "Over 2.5 Goals Combo",
                predictionValue = "5-Match Over 2.5 Goals Acca",
                odds = "11.20",
                matchDate = "Oct 11, 2026",
                kickoffTime = "14:30",
                category = TipCategory.VIP_10_PLUS,
                resultState = TipResult.PENDING,
                createdAt = nextTime()
            ),

            // ==================== 10+ TIPS VIP - HISTORY ====================
            TipItem(
                id = "vip_10p_hist_1",
                homeTeam = "Weekend High-Odds Combo",
                awayTeam = "4-Fold Parlay",
                league = "Multi-League",
                predictionType = "Accumulator",
                predictionValue = "4 Matches Clean Sheet Win",
                odds = "10.85",
                matchDate = "Oct 06, 2026",
                kickoffTime = "18:00",
                category = TipCategory.VIP_10_PLUS,
                resultState = TipResult.WON,
                createdAt = nextTime()
            ),
            TipItem(
                id = "vip_10p_hist_2",
                homeTeam = "Champions Midweek Accumulator",
                awayTeam = "5-Fold Acca",
                league = "UEFA Champions League",
                predictionType = "Accumulator",
                predictionValue = "5 Selections Full Time",
                odds = "12.30",
                matchDate = "Oct 04, 2026",
                kickoffTime = "20:00",
                category = TipCategory.VIP_10_PLUS,
                resultState = TipResult.LOST,
                createdAt = nextTime()
            ),

            // ==================== CORRECT SCORE VIP - CURRENT (PENDING) ====================
            TipItem(
                id = "vip_cs_curr_1",
                homeTeam = "Liverpool",
                awayTeam = "West Ham",
                league = "Premier League",
                predictionType = "Exact Score",
                predictionValue = "2 - 0",
                odds = "7.50",
                matchDate = "Oct 10, 2026",
                kickoffTime = "15:00",
                category = TipCategory.VIP_CORRECT_SCORE,
                resultState = TipResult.PENDING,
                createdAt = nextTime()
            ),
            TipItem(
                id = "vip_cs_curr_2",
                homeTeam = "Barcelona",
                awayTeam = "Girona",
                league = "La Liga",
                predictionType = "Exact Score",
                predictionValue = "3 - 1",
                odds = "11.00",
                matchDate = "Oct 11, 2026",
                kickoffTime = "20:00",
                category = TipCategory.VIP_CORRECT_SCORE,
                resultState = TipResult.PENDING,
                createdAt = nextTime()
            ),
            TipItem(
                id = "vip_cs_curr_3",
                homeTeam = "Napoli",
                awayTeam = "Monza",
                league = "Serie A",
                predictionType = "Exact Score",
                predictionValue = "2 - 1",
                odds = "8.25",
                matchDate = "Oct 11, 2026",
                kickoffTime = "17:00",
                category = TipCategory.VIP_CORRECT_SCORE,
                resultState = TipResult.PENDING,
                createdAt = nextTime()
            ),

            // ==================== CORRECT SCORE VIP - HISTORY ====================
            TipItem(
                id = "vip_cs_hist_1",
                homeTeam = "Manchester United",
                awayTeam = "Fulham",
                league = "Premier League",
                predictionType = "Exact Score",
                predictionValue = "1 - 0",
                odds = "7.00",
                matchDate = "Oct 08, 2026",
                kickoffTime = "19:00",
                category = TipCategory.VIP_CORRECT_SCORE,
                resultState = TipResult.WON,
                createdAt = nextTime()
            ),
            TipItem(
                id = "vip_cs_hist_2",
                homeTeam = "Bayern Munich",
                awayTeam = "Wolfsburg",
                league = "Bundesliga",
                predictionType = "Exact Score",
                predictionValue = "3 - 0",
                odds = "8.50",
                matchDate = "Oct 07, 2026",
                kickoffTime = "14:30",
                category = TipCategory.VIP_CORRECT_SCORE,
                resultState = TipResult.LOST,
                createdAt = nextTime()
            ),
            TipItem(
                id = "vip_cs_hist_3",
                homeTeam = "Real Madrid",
                awayTeam = "Alaves",
                league = "La Liga",
                predictionType = "Exact Score",
                predictionValue = "2 - 0",
                odds = "6.50",
                matchDate = "Oct 05, 2026",
                kickoffTime = "20:00",
                category = TipCategory.VIP_CORRECT_SCORE,
                resultState = TipResult.WON,
                createdAt = nextTime()
            )
        )
    }
}
