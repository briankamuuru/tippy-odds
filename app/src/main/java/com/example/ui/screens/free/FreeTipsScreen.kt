package com.example.ui.screens.free

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.model.TipItem
import com.example.localization.AppLanguage
import com.example.localization.Strings
import com.example.ui.FreeTipsTab
import com.example.ui.components.DayGroupedTipsList
import com.example.ui.components.SegmentedTabControl
import com.example.ui.theme.OffWhite

@Composable
fun FreeTipsScreen(
    currentTab: FreeTipsTab,
    onTabSelected: (FreeTipsTab) -> Unit,
    currentTips: List<TipItem>,
    historyTips: List<TipItem>,
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(OffWhite)
            .padding(horizontal = 12.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Tab Selector styled as an orange box
        SegmentedTabControl(
            tabs = listOf(
                Strings.tabCurrentFreeTips(language),
                Strings.tabFreeTipsHistory(language)
            ),
            selectedIndex = if (currentTab == FreeTipsTab.CURRENT) 0 else 1,
            onTabSelected = { index ->
                onTabSelected(if (index == 0) FreeTipsTab.CURRENT else FreeTipsTab.HISTORY)
            },
            useOrangeStyle = true,
            modifier = Modifier.testTag("free_tips_tab_selector")
        )

        Spacer(modifier = Modifier.height(10.dp))

        val tipsToShow = if (currentTab == FreeTipsTab.CURRENT) currentTips else historyTips
        val emptyMessage = if (currentTab == FreeTipsTab.CURRENT) {
            Strings.emptyActiveTips(language)
        } else {
            Strings.emptyHistoryTips(language)
        }

        // Day-grouped tips list: odds of same day under date, followed by accumulated odds box
        DayGroupedTipsList(
            tips = tipsToShow,
            language = language,
            emptyMessage = emptyMessage,
            modifier = Modifier.fillMaxSize()
        )
    }
}
