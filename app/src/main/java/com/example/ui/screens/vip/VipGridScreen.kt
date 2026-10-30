package com.example.ui.screens.vip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Scoreboard
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.AppLanguage
import com.example.localization.Strings
import com.example.ui.VipSubSection
import com.example.ui.theme.BorderLight
import com.example.ui.theme.CardBackground
import com.example.ui.theme.NavyDark
import com.example.ui.theme.OffWhite
import com.example.ui.theme.OrangeBorder
import com.example.ui.theme.OrangeContainer
import com.example.ui.theme.OrangeLight
import com.example.ui.theme.OrangePrimary
import com.example.ui.theme.PureWhite
import com.example.ui.theme.TextPrimary

@Composable
fun VipGridScreen(
    onSelectSubSection: (VipSubSection) -> Unit,
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(OffWhite)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Row 1: 2+ Tips VIP, 5+ Tips VIP (Orange boxes)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            VipGridCard(
                title = Strings.vip2Plus(language),
                icon = Icons.Default.TrendingUp,
                onClick = { onSelectSubSection(VipSubSection.VIP_2_PLUS) },
                testTag = "vip_item_2_plus",
                modifier = Modifier.weight(1f)
            )
            VipGridCard(
                title = Strings.vip5Plus(language),
                icon = Icons.Default.Whatshot,
                onClick = { onSelectSubSection(VipSubSection.VIP_5_PLUS) },
                testTag = "vip_item_5_plus",
                modifier = Modifier.weight(1f)
            )
        }

        // Row 2: 10+ Tips VIP, Correct Score VIP (Orange boxes)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            VipGridCard(
                title = Strings.vip10Plus(language),
                icon = Icons.Default.WorkspacePremium,
                onClick = { onSelectSubSection(VipSubSection.VIP_10_PLUS) },
                testTag = "vip_item_10_plus",
                modifier = Modifier.weight(1f)
            )
            VipGridCard(
                title = Strings.vipCorrectScore(language),
                icon = Icons.Default.Scoreboard,
                onClick = { onSelectSubSection(VipSubSection.VIP_CORRECT_SCORE) },
                testTag = "vip_item_correct_score",
                modifier = Modifier.weight(1f)
            )
        }

        // Row 3: History VIP (Orange box, occupying one grid position, second position empty)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            VipGridCard(
                title = Strings.vipHistory(language),
                icon = Icons.Default.History,
                onClick = { onSelectSubSection(VipSubSection.VIP_HISTORY) },
                testTag = "vip_item_history",
                modifier = Modifier.weight(1f)
            )
            // Empty placeholder for second position in Row 3 (per spec: "remaining position may remain empty")
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun VipGridCard(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    testTag: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(105.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(OrangeLight)
            .border(width = 1.5.dp, color = OrangeBorder, shape = RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(12.dp)
            .testTag(testTag),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(OrangeContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = OrangePrimary,
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    textAlign = TextAlign.Center
                ),
                maxLines = 1
            )
        }
    }
}
