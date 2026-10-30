package com.example.ui.screens.vip

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.data.model.TipItem
import com.example.localization.AppLanguage
import com.example.localization.Strings
import com.example.ui.VipHistoryCategory
import com.example.ui.VipSubSection

@Composable
fun VipTipsScreen(
    activeSubSection: VipSubSection?,
    onSelectSubSection: (VipSubSection) -> Unit,
    active2PlusTips: List<TipItem>,
    active5PlusTips: List<TipItem>,
    active10PlusTips: List<TipItem>,
    activeCorrectScoreTips: List<TipItem>,
    vipHistoryCategory: VipHistoryCategory,
    onVipHistoryCategorySelected: (VipHistoryCategory) -> Unit,
    history2Plus: List<TipItem>,
    history5Plus: List<TipItem>,
    history10Plus: List<TipItem>,
    historyCorrectScore: List<TipItem>,
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (activeSubSection) {
            null -> {
                VipGridScreen(
                    onSelectSubSection = onSelectSubSection,
                    language = language
                )
            }
            VipSubSection.VIP_2_PLUS -> {
                VipSubSectionListScreen(
                    tips = active2PlusTips,
                    subSectionTitle = Strings.vip2Plus(language),
                    language = language
                )
            }
            VipSubSection.VIP_5_PLUS -> {
                VipSubSectionListScreen(
                    tips = active5PlusTips,
                    subSectionTitle = Strings.vip5Plus(language),
                    language = language
                )
            }
            VipSubSection.VIP_10_PLUS -> {
                VipSubSectionListScreen(
                    tips = active10PlusTips,
                    subSectionTitle = Strings.vip10Plus(language),
                    language = language
                )
            }
            VipSubSection.VIP_CORRECT_SCORE -> {
                VipSubSectionListScreen(
                    tips = activeCorrectScoreTips,
                    subSectionTitle = Strings.vipCorrectScore(language),
                    language = language
                )
            }
            VipSubSection.VIP_HISTORY -> {
                VipHistoryScreen(
                    currentCategory = vipHistoryCategory,
                    onCategorySelected = onVipHistoryCategorySelected,
                    history2Plus = history2Plus,
                    history5Plus = history5Plus,
                    history10Plus = history10Plus,
                    historyCorrectScore = historyCorrectScore,
                    language = language
                )
            }
        }
    }
}
