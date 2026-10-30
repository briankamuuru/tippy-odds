package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.local.AppDatabase
import com.example.data.preferences.AppPreferences
import com.example.data.repository.TipRepository
import com.example.localization.AppLanguage
import com.example.localization.Strings
import com.example.ui.AppSection
import com.example.ui.MainViewModel
import com.example.ui.MainViewModelFactory
import com.example.ui.SettingsSubScreen
import com.example.ui.VipSubSection
import com.example.ui.components.TippyBottomNavigation
import com.example.ui.components.TippyTopAppBar
import com.example.ui.screens.free.FreeTipsScreen
import com.example.ui.screens.settings.AboutScreen
import com.example.ui.screens.settings.PrivacyPolicyScreen
import com.example.ui.screens.settings.SettingsScreen
import com.example.ui.screens.vip.VipTipsScreen
import com.example.ui.theme.OffWhite
import com.example.ui.theme.TippyOddsTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels {
        val database = AppDatabase.getInstance(applicationContext)
        val repository = TipRepository(database.tipDao())
        val preferences = AppPreferences(applicationContext)
        MainViewModelFactory(repository, preferences)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val language by viewModel.selectedLanguage.collectAsStateWithLifecycle()
            val layoutDirection = if (language.isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr

            CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                TippyOddsTheme {
                    MainAppContent(viewModel = viewModel, language = language)
                }
            }
        }
    }
}

@Composable
fun MainAppContent(
    viewModel: MainViewModel,
    language: AppLanguage
) {
    val currentSection by viewModel.currentSection.collectAsStateWithLifecycle()
    val freeTipsTab by viewModel.freeTipsTab.collectAsStateWithLifecycle()
    val activeVipSubSection by viewModel.activeVipSubSection.collectAsStateWithLifecycle()
    val vipHistoryCategory by viewModel.activeVipHistoryCategory.collectAsStateWithLifecycle()
    val settingsSubScreen by viewModel.settingsSubScreen.collectAsStateWithLifecycle()

    val currentFreeTips by viewModel.currentFreeTips.collectAsStateWithLifecycle()
    val freeTipsHistory by viewModel.freeTipsHistory.collectAsStateWithLifecycle()

    val active2PlusTips by viewModel.activeVip2PlusTips.collectAsStateWithLifecycle()
    val active5PlusTips by viewModel.activeVip5PlusTips.collectAsStateWithLifecycle()
    val active10PlusTips by viewModel.activeVip10PlusTips.collectAsStateWithLifecycle()
    val activeCorrectScoreTips by viewModel.activeCorrectScoreTips.collectAsStateWithLifecycle()

    val history2Plus by viewModel.vipHistory2PlusTips.collectAsStateWithLifecycle()
    val history5Plus by viewModel.vipHistory5PlusTips.collectAsStateWithLifecycle()
    val history10Plus by viewModel.vipHistory10PlusTips.collectAsStateWithLifecycle()
    val historyCorrectScore by viewModel.vipHistoryCorrectScoreTips.collectAsStateWithLifecycle()

    // Determine back navigation capability
    val canNavigateBack = (currentSection == AppSection.VIP_TIPS && activeVipSubSection != null) ||
            (currentSection == AppSection.SETTINGS && settingsSubScreen != null)

    BackHandler(enabled = canNavigateBack || currentSection != AppSection.FREE_TIPS) {
        viewModel.handleBackPress()
    }

    val topBarTitle = when (currentSection) {
        AppSection.FREE_TIPS -> "TIPPY ODDS"
        AppSection.VIP_TIPS -> when (activeVipSubSection) {
            null -> Strings.navVipTips(language)
            VipSubSection.VIP_2_PLUS -> Strings.vip2Plus(language)
            VipSubSection.VIP_5_PLUS -> Strings.vip5Plus(language)
            VipSubSection.VIP_10_PLUS -> Strings.vip10Plus(language)
            VipSubSection.VIP_CORRECT_SCORE -> Strings.vipCorrectScore(language)
            VipSubSection.VIP_HISTORY -> Strings.vipHistory(language)
        }
        AppSection.SETTINGS -> when (settingsSubScreen) {
            null -> Strings.navSettings(language)
            SettingsSubScreen.PRIVACY_POLICY -> Strings.settingsPrivacyPolicy(language)
            SettingsSubScreen.ABOUT -> Strings.settingsAbout(language)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(OffWhite),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            TippyTopAppBar(
                title = topBarTitle,
                canNavigateBack = canNavigateBack,
                onNavigateBack = { viewModel.handleBackPress() }
            )
        },
        bottomBar = {
            TippyBottomNavigation(
                currentSection = currentSection,
                onSectionSelected = { viewModel.selectSection(it) },
                language = language
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(OffWhite)
        ) {
            when (currentSection) {
                AppSection.FREE_TIPS -> {
                    FreeTipsScreen(
                        currentTab = freeTipsTab,
                        onTabSelected = { viewModel.setFreeTipsTab(it) },
                        currentTips = currentFreeTips,
                        historyTips = freeTipsHistory,
                        language = language
                    )
                }
                AppSection.VIP_TIPS -> {
                    VipTipsScreen(
                        activeSubSection = activeVipSubSection,
                        onSelectSubSection = { viewModel.openVipSubSection(it) },
                        active2PlusTips = active2PlusTips,
                        active5PlusTips = active5PlusTips,
                        active10PlusTips = active10PlusTips,
                        activeCorrectScoreTips = activeCorrectScoreTips,
                        vipHistoryCategory = vipHistoryCategory,
                        onVipHistoryCategorySelected = { viewModel.setVipHistoryCategory(it) },
                        history2Plus = history2Plus,
                        history5Plus = history5Plus,
                        history10Plus = history10Plus,
                        historyCorrectScore = historyCorrectScore,
                        language = language
                    )
                }
                AppSection.SETTINGS -> {
                    when (settingsSubScreen) {
                        null -> {
                            SettingsScreen(
                                currentLanguage = language,
                                onLanguageSelected = { viewModel.setLanguage(it) },
                                onOpenSubScreen = { viewModel.openSettingsSubScreen(it) }
                            )
                        }
                        SettingsSubScreen.PRIVACY_POLICY -> {
                            PrivacyPolicyScreen(language = language)
                        }
                        SettingsSubScreen.ABOUT -> {
                            AboutScreen(language = language)
                        }
                    }
                }
            }
        }
    }
}
