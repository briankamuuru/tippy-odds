package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Functions
import androidx.compose.material.icons.outlined.Pin
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.TipItem
import com.example.localization.AppLanguage
import com.example.localization.Strings
import com.example.ui.theme.BorderLight
import com.example.ui.theme.DateBlue
import com.example.ui.theme.NavyDark
import com.example.ui.theme.OrangeBorder
import com.example.ui.theme.OrangeContainer
import com.example.ui.theme.OrangeLight
import com.example.ui.theme.OrangePrimary
import com.example.ui.theme.PureWhite
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import java.util.Locale

@Composable
fun DayGroupedTipsList(
    tips: List<TipItem>,
    language: AppLanguage,
    emptyMessage: String,
    modifier: Modifier = Modifier
) {
    if (tips.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .testTag("grouped_tips_empty_state"),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = emptyMessage,
                fontSize = 13.sp,
                color = TextMuted
            )
        }
        return
    }

    // Group tips by matchDate preserving order
    val groupedByDate = tips.groupBy { it.matchDate }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("grouped_tips_list"),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        groupedByDate.forEach { (date, dayTips) ->
            // 1. Day Date Header Box (Orange themed date header box)
            item(key = "header_$date") {
                DayDateHeaderBox(date = date)
            }

            // 2. All odds of that same day appear under that day date
            items(
                count = dayTips.size,
                key = { index -> dayTips[index].id }
            ) { index ->
                CompactTipCard(
                    tip = dayTips[index],
                    language = language
                )
            }

            // 3. At the end of that date odds: a box showing the total accumulated odds and number of odds only
            item(key = "summary_$date") {
                DayOddsSummaryBox(
                    dayTips = dayTips,
                    language = language,
                    date = date
                )
            }
        }
    }
}

/**
 * Orange box header for the match date.
 */
@Composable
fun DayDateHeaderBox(
    date: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(OrangeContainer)
            .border(width = 1.dp, color = OrangeBorder, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 7.dp)
            .testTag("date_header_$date")
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.CalendarToday,
                contentDescription = null,
                tint = DateBlue,
                modifier = Modifier.size(15.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = date,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = DateBlue
                )
            )
        }
    }
}

/**
 * Box showing total accumulated odds and number of odds only at the end of that date's odds.
 * Styled as an orange box per user request ("in sections and subsection use orange boxes").
 */
@Composable
fun DayOddsSummaryBox(
    dayTips: List<TipItem>,
    language: AppLanguage,
    date: String,
    modifier: Modifier = Modifier
) {
    // Calculate accumulated (multiplier) odds across all selections for this date
    val totalAccumulated = dayTips.fold(1.0) { acc, tip ->
        val oddVal = tip.odds.toDoubleOrNull() ?: 1.0
        acc * oddVal
    }
    val formattedAccumulated = String.format(Locale.US, "%.2f", totalAccumulated)
    val numberOfOdds = dayTips.size

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(OrangeLight)
            .border(width = 1.5.dp, color = OrangeBorder, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp)
            .testTag("day_odds_summary_box_$date")
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Metric 1: Total Accumulated Odds
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Functions,
                    contentDescription = null,
                    tint = OrangePrimary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                    Text(
                        text = Strings.totalAccumulatedOdds(language),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextSecondary
                    )
                    Text(
                        text = formattedAccumulated,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = OrangePrimary
                    )
                }
            }

            // Metric 2: Number of Odds Only
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Pin,
                    contentDescription = null,
                    tint = NavyDark,
                    modifier = Modifier.size(17.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = Strings.numberOfOdds(language),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextSecondary
                    )
                    Text(
                        text = "$numberOfOdds",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = NavyDark
                    )
                }
            }
        }
    }
}
