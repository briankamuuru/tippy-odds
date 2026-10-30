package com.example.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.localization.AppLanguage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppPreferences(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("tippy_odds_prefs", Context.MODE_PRIVATE)

    private val _selectedLanguage = MutableStateFlow(loadLanguage())
    val selectedLanguage: StateFlow<AppLanguage> = _selectedLanguage.asStateFlow()

    private fun loadLanguage(): AppLanguage {
        val code = prefs.getString(KEY_LANGUAGE, AppLanguage.EN.code) ?: AppLanguage.EN.code
        return AppLanguage.fromCode(code)
    }

    fun setLanguage(language: AppLanguage) {
        prefs.edit().putString(KEY_LANGUAGE, language.code).apply()
        _selectedLanguage.value = language
    }

    companion object {
        private const val KEY_LANGUAGE = "selected_language"
    }
}
