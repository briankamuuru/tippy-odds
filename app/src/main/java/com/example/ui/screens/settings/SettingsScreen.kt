package com.example.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.BuildConfig
import com.example.localization.AppLanguage
import com.example.localization.Strings
import com.example.ui.SettingsSubScreen
import com.example.ui.theme.BorderLight
import com.example.ui.theme.CardBackground
import com.example.ui.theme.NavyDark
import com.example.ui.theme.OffWhite
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun SettingsScreen(
    currentLanguage: AppLanguage,
    onLanguageSelected: (AppLanguage) -> Unit,
    onOpenSubScreen: (SettingsSubScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    var showLanguageDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(OffWhite)
            .padding(16.dp)
            .testTag("settings_screen")
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(CardBackground)
                .border(width = 1.dp, color = BorderLight, shape = RoundedCornerShape(8.dp))
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // 1. Language
                SettingsRow(
                    icon = Icons.Outlined.Language,
                    label = Strings.settingsLanguage(currentLanguage),
                    value = currentLanguage.displayName,
                    onClick = { showLanguageDialog = true },
                    testTag = "settings_item_language"
                )

                HorizontalDivider(thickness = 1.dp, color = BorderLight)

                // 2. Version
                SettingsRow(
                    icon = Icons.Outlined.Numbers,
                    label = Strings.settingsVersion(currentLanguage),
                    value = "Version ${BuildConfig.VERSION_NAME}",
                    onClick = null,
                    testTag = "settings_item_version"
                )

                HorizontalDivider(thickness = 1.dp, color = BorderLight)

                // 3. Privacy Policy
                SettingsRow(
                    icon = Icons.Outlined.PrivacyTip,
                    label = Strings.settingsPrivacyPolicy(currentLanguage),
                    value = null,
                    onClick = { onOpenSubScreen(SettingsSubScreen.PRIVACY_POLICY) },
                    testTag = "settings_item_privacy_policy"
                )

                HorizontalDivider(thickness = 1.dp, color = BorderLight)

                // 4. About
                SettingsRow(
                    icon = Icons.Outlined.Info,
                    label = Strings.settingsAbout(currentLanguage),
                    value = null,
                    onClick = { onOpenSubScreen(SettingsSubScreen.ABOUT) },
                    testTag = "settings_item_about"
                )
            }
        }
    }

    if (showLanguageDialog) {
        LanguageSelectionDialog(
            currentLanguage = currentLanguage,
            onLanguageSelected = onLanguageSelected,
            onDismiss = { showLanguageDialog = false }
        )
    }
}

@Composable
private fun SettingsRow(
    icon: ImageVector,
    label: String,
    value: String?,
    onClick: (() -> Unit)?,
    testTag: String
) {
    val rowModifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .testTag(testTag)
        .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
        .padding(horizontal = 16.dp)

    Row(
        modifier = rowModifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = NavyDark,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = label,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = TextPrimary
            ),
            modifier = Modifier.weight(1f)
        )

        if (value != null) {
            Text(
                text = value,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 13.sp,
                    color = TextSecondary
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
        }

        if (onClick != null) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = TextMuted,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
