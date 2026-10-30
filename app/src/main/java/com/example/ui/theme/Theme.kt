package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val CleanLightColorScheme = lightColorScheme(
    primary = NavyDark,
    onPrimary = PureWhite,
    primaryContainer = Slate100,
    onPrimaryContainer = NavyDark,
    secondary = NavyMedium,
    onSecondary = PureWhite,
    secondaryContainer = Slate100,
    onSecondaryContainer = NavyMedium,
    background = OffWhite,
    onBackground = TextPrimary,
    surface = PureWhite,
    onSurface = TextPrimary,
    surfaceVariant = OffWhite,
    onSurfaceVariant = TextSecondary,
    outline = BorderLight,
    outlineVariant = BorderStrong
)

@Composable
fun TippyOddsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // The brief mandates a clean white or very light background with high readability
    MaterialTheme(
        colorScheme = CleanLightColorScheme,
        typography = Typography,
        content = content
    )
}
