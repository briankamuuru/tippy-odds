package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.TipCategory
import com.example.data.model.TipItem
import com.example.data.model.TipResult
import com.example.data.preferences.AppPreferences
import com.example.data.repository.TipRepository
import com.example.localization.AppLanguage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppSection {
    FREE_TIPS,
    VIP_TIPS,
    SETTINGS
}

enum class FreeTipsTab {
    CURRENT,
    HISTORY
}

enum class VipSubSection {
    VIP_2_PLUS,
    VIP_5_PLUS,
    VIP_10_PLUS,
    VIP_CORRECT_SCORE,
    VIP_HISTORY
}

enum class VipHistoryCategory {
    VIP_2_PLUS,
    VIP_5_PLUS,
    VIP_10_PLUS,
    VIP_CORRECT_SCORE
}

enum class SettingsSubScreen {
    PRIVACY_POLICY,
    ABOUT
}

class MainViewModel(
    private val repository: TipRepository,
    private val preferences: AppPreferences
) : ViewModel() {

    // Current navigation section (Bottom bar)
    private val _currentSection = MutableStateFlow(AppSection.FREE_TIPS)
    val currentSection: StateFlow<AppSection> = _currentSection.asStateFlow()

    // Free Tips Tab
    private val _freeTipsTab = MutableStateFlow(FreeTipsTab.CURRENT)
    val freeTipsTab: StateFlow<FreeTipsTab> = _freeTipsTab.asStateFlow()

    // VIP active sub-section (null when showing the 2x3 grid)
    private val _activeVipSubSection = MutableStateFlow<VipSubSection?>(null)
    val activeVipSubSection: StateFlow<VipSubSection?> = _activeVipSubSection.asStateFlow()

    // VIP History active category (for the 4 independent categories inside History VIP)
    private val _activeVipHistoryCategory = MutableStateFlow(VipHistoryCategory.VIP_2_PLUS)
    val activeVipHistoryCategory: StateFlow<VipHistoryCategory> = _activeVipHistoryCategory.asStateFlow()

    // Settings sub-screen
    private val _settingsSubScreen = MutableStateFlow<SettingsSubScreen?>(null)
    val settingsSubScreen: StateFlow<SettingsSubScreen?> = _settingsSubScreen.asStateFlow()

    // Language
    val selectedLanguage: StateFlow<AppLanguage> = preferences.selectedLanguage

    // Tip Flows
    val currentFreeTips: StateFlow<List<TipItem>> = repository.getCurrentFreeTips()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val freeTipsHistory: StateFlow<List<TipItem>> = repository.getFreeTipsHistory()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeVip2PlusTips: StateFlow<List<TipItem>> = repository.getActiveVipTips(TipCategory.VIP_2_PLUS)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeVip5PlusTips: StateFlow<List<TipItem>> = repository.getActiveVipTips(TipCategory.VIP_5_PLUS)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeVip10PlusTips: StateFlow<List<TipItem>> = repository.getActiveVipTips(TipCategory.VIP_10_PLUS)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeCorrectScoreTips: StateFlow<List<TipItem>> = repository.getActiveVipTips(TipCategory.VIP_CORRECT_SCORE)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val vipHistory2PlusTips: StateFlow<List<TipItem>> = repository.getVipHistory(TipCategory.VIP_2_PLUS)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val vipHistory5PlusTips: StateFlow<List<TipItem>> = repository.getVipHistory(TipCategory.VIP_5_PLUS)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val vipHistory10PlusTips: StateFlow<List<TipItem>> = repository.getVipHistory(TipCategory.VIP_10_PLUS)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val vipHistoryCorrectScoreTips: StateFlow<List<TipItem>> = repository.getVipHistory(TipCategory.VIP_CORRECT_SCORE)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allTips: StateFlow<List<TipItem>> = repository.getAllTips()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            repository.initializeIfEmpty()
        }
    }

    fun selectSection(section: AppSection) {
        _currentSection.value = section
        if (section == AppSection.SETTINGS) {
            _settingsSubScreen.value = null
        }
    }

    fun setFreeTipsTab(tab: FreeTipsTab) {
        _freeTipsTab.value = tab
    }

    fun openVipSubSection(subSection: VipSubSection) {
        _activeVipSubSection.value = subSection
    }

    fun closeVipSubSection() {
        _activeVipSubSection.value = null
    }

    fun setVipHistoryCategory(category: VipHistoryCategory) {
        _activeVipHistoryCategory.value = category
    }

    fun openSettingsSubScreen(subScreen: SettingsSubScreen) {
        _settingsSubScreen.value = subScreen
    }

    fun closeSettingsSubScreen() {
        _settingsSubScreen.value = null
    }

    fun setLanguage(language: AppLanguage) {
        preferences.setLanguage(language)
    }

    fun updateTipResult(tipId: String, newResult: TipResult) {
        viewModelScope.launch {
            repository.updateTipResult(tipId, newResult)
        }
    }

    fun resetAllTips() {
        viewModelScope.launch {
            repository.resetDatabaseToInitial()
        }
    }

    fun handleBackPress(): Boolean {
        if (_settingsSubScreen.value != null) {
            _settingsSubScreen.value = null
            return true
        }
        if (_activeVipSubSection.value != null) {
            _activeVipSubSection.value = null
            return true
        }
        if (_currentSection.value != AppSection.FREE_TIPS) {
            _currentSection.value = AppSection.FREE_TIPS
            return true
        }
        return false
    }
}
