package com.example.ui.screens.vip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
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
import com.example.ui.VipHistoryCategory
import com.example.ui.components.DayGroupedTipsList
import com.example.ui.components.SegmentedTabControl
import com.example.ui.theme.OffWhite
import com.example.ui.theme.OrangeBorder
import com.example.ui.theme.OrangeContainer
import com.example.ui.theme.OrangeLight
import com.example.ui.theme.OrangePrimary

@Composable
fun VipHistoryScreen(
    currentCategory: VipHistoryCategory,
    onCategorySelected: (VipHistoryCategory) -> Unit,
    history2Plus: List<TipItem>,
    history5Plus: List<TipItem>,
    history10Plus: List<TipItem>,
    historyCorrectScore: List<TipItem>,
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    val categories = listOf(
        VipHistoryCategory.VIP_2_PLUS,
        VipHistoryCategory.VIP_5_PLUS,
        VipHistoryCategory.VIP_10_PLUS,
        VipHistoryCategory.VIP_CORRECT_SCORE
    )

    val categoryTitles = listOf(
        Strings.vipHistory2Plus(language),
        Strings.vipHistory5Plus(language),
        Strings.vipHistory10Plus(language),
        Strings.vipHistoryCorrectScore(language)
    )

    val selectedIndex = categories.indexOf(currentCategory).coerceAtLeast(0)

    val tipsToShow = when (currentCategory) {
        VipHistoryCategory.VIP_2_PLUS -> history2Plus
        VipHistoryCategory.VIP_5_PLUS -> history5Plus
        VipHistoryCategory.VIP_10_PLUS -> history10Plus
        VipHistoryCategory.VIP_CORRECT_SCORE -> historyCorrectScore
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(OffWhite)
            .padding(horizontal = 12.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Subsection Orange Header Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(OrangeLight)
                .border(width = 1.2.dp, color = OrangeBorder, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .testTag("vip_history_header_box")
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(OrangeContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = null,
                        tint = OrangePrimary,
                        modifier = Modifier.size(14.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = Strings.vipHistory(language),
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = OrangePrimary
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 4 Independent categories compact selector (orange box styled)
        SegmentedTabControl(
            tabs = categoryTitles,
            selectedIndex = selectedIndex,
            onTabSelected = { index ->
                if (index in categories.indices) {
                    onCategorySelected(categories[index])
                }
            },
            useOrangeStyle = true,
            modifier = Modifier.testTag("vip_history_category_selector")
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Day-grouped tips list: odds of same day under date, followed by accumulated odds box
        DayGroupedTipsList(
            tips = tipsToShow,
            language = language,
            emptyMessage = Strings.emptyHistoryTips(language),
            modifier = Modifier.fillMaxSize()
        )
    }
}
