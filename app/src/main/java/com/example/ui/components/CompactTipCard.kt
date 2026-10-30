package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.TipCategory
import com.example.data.model.TipItem
import com.example.data.model.TipResult
import com.example.localization.AppLanguage
import com.example.localization.Strings
import com.example.ui.theme.BorderLight
import com.example.ui.theme.CardBackground
import com.example.ui.theme.DateBlue
import com.example.ui.theme.LeagueBlue
import com.example.ui.theme.ResultLostRed
import com.example.ui.theme.ResultWonGreen
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun CompactTipCard(
    tip: TipItem,
    language: AppLanguage,
    modifier: Modifier = Modifier,
    onLongClick: (() -> Unit)? = null
) {
    val oddsColor = when (tip.resultState) {
        TipResult.PENDING -> TextPrimary
        TipResult.WON -> ResultWonGreen
        TipResult.LOST -> ResultLostRed
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(CardBackground)
            .border(width = 1.dp, color = BorderLight, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .testTag("tip_card_${tip.id}")
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Row 1: League (blue) and Match Date (blue) / Kickoff Time
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (tip.league.isNotBlank()) {
                    Text(
                        text = tip.league,
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = LeagueBlue
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f, fill = false)
                    )
                } else {
                    Spacer(modifier = Modifier.width(1.dp))
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = tip.matchDate,
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = DateBlue
                        )
                    )
                    Text(
                        text = " • ${tip.kickoffTime}",
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 11.sp,
                            color = TextMuted
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Row 2: Teams (Home vs Away)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${tip.homeTeam} vs ${tip.awayTeam}",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Row 3: Prediction on the left, Odds and Checkbox on the right
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Prediction pill/text
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f, fill = false)
                ) {
                    Text(
                        text = "${Strings.tipLabel(language)}: ",
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = TextSecondary
                        )
                    )
                    Text(
                        text = if (tip.category == TipCategory.VIP_CORRECT_SCORE) {
                            tip.predictionValue
                        } else {
                            tip.predictionValue
                        },
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Odds and Checkbox
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = tip.odds,
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = oddsColor
                        ),
                        modifier = Modifier.testTag("odds_${tip.id}")
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    ResultCheckbox(
                        resultState = tip.resultState,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}
