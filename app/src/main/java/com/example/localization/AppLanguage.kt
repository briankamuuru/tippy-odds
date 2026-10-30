package com.example.localization

enum class AppLanguage(val code: String, val displayName: String, val nativeName: String, val isRtl: Boolean) {
    EN("en", "English", "English", false),
    AR("ar", "Arabic", "العربية", true),
    ES("es", "Spanish", "Español", false),
    DE("de", "German", "Deutsch", false);

    companion object {
        fun fromCode(code: String): AppLanguage {
            return entries.firstOrNull { it.code.equals(code, ignoreCase = true) } ?: EN
        }
    }
}
