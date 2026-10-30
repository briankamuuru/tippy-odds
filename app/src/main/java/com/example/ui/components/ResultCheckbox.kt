package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.model.TipResult
import com.example.ui.theme.BorderStrong
import com.example.ui.theme.PureWhite
import com.example.ui.theme.ResultLostRed
import com.example.ui.theme.ResultWonGreen

/**
 * Small visual checkbox result indicator.
 * - Pending: Empty, unticked box.
 * - Won: Ticked checkbox (green border/fill).
 * - Lost: Ticked checkbox (red border/fill).
 * Non-clickable for standard users; serves strictly as an indicator of declared match outcome.
 */
@Composable
fun ResultCheckbox(
    resultState: TipResult,
    modifier: Modifier = Modifier
) {
    val isTicked = resultState.isCompleted
    val borderColor = when (resultState) {
        TipResult.PENDING -> BorderStrong
        TipResult.WON -> ResultWonGreen
        TipResult.LOST -> ResultLostRed
    }
    val backgroundColor = when (resultState) {
        TipResult.PENDING -> PureWhite
        TipResult.WON -> ResultWonGreen
        TipResult.LOST -> ResultLostRed
    }

    Box(
        modifier = modifier
            .size(18.dp)
            .clip(RoundedCornerShape(3.dp))
            .border(width = 1.2.dp, color = borderColor, shape = RoundedCornerShape(3.dp))
            .background(backgroundColor)
            .testTag("result_checkbox_${resultState.key.lowercase()}"),
        contentAlignment = Alignment.Center
    ) {
        if (isTicked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Match Result Declared",
                tint = PureWhite,
                modifier = Modifier.size(13.dp)
            )
        }
    }
}
