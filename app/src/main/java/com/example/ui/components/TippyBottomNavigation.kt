package com.example.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.SportsSoccer
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.AppLanguage
import com.example.localization.Strings
import com.example.ui.AppSection
import com.example.ui.theme.BorderLight
import com.example.ui.theme.NavyDark
import com.example.ui.theme.PureWhite
import com.example.ui.theme.Slate100
import com.example.ui.theme.TextMuted

@Composable
fun TippyBottomNavigation(
    currentSection: AppSection,
    onSectionSelected: (AppSection) -> Unit,
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
            .border(width = 1.dp, color = BorderLight)
            .height(64.dp),
        containerColor = PureWhite,
        tonalElevation = 0.dp
    ) {
        // 1. Free Tips
        val isFreeSelected = currentSection == AppSection.FREE_TIPS
        NavigationBarItem(
            selected = isFreeSelected,
            onClick = { onSectionSelected(AppSection.FREE_TIPS) },
            icon = {
                Icon(
                    imageVector = if (isFreeSelected) Icons.Filled.SportsSoccer else Icons.Outlined.SportsSoccer,
                    contentDescription = Strings.navFreeTips(language),
                    modifier = Modifier.size(20.dp)
                )
            },
            label = {
                Text(
                    text = Strings.navFreeTips(language),
                    fontSize = 11.sp,
                    fontWeight = if (isFreeSelected) FontWeight.Bold else FontWeight.Medium
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = NavyDark,
                selectedTextColor = NavyDark,
                indicatorColor = Slate100,
                unselectedIconColor = TextMuted,
                unselectedTextColor = TextMuted
            ),
            modifier = Modifier.testTag("nav_free_tips")
        )

        // 2. VIP Tips
        val isVipSelected = currentSection == AppSection.VIP_TIPS
        NavigationBarItem(
            selected = isVipSelected,
            onClick = { onSectionSelected(AppSection.VIP_TIPS) },
            icon = {
                Icon(
                    imageVector = if (isVipSelected) Icons.Filled.Star else Icons.Outlined.StarBorder,
                    contentDescription = Strings.navVipTips(language),
                    modifier = Modifier.size(20.dp)
                )
            },
            label = {
                Text(
                    text = Strings.navVipTips(language),
                    fontSize = 11.sp,
                    fontWeight = if (isVipSelected) FontWeight.Bold else FontWeight.Medium
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = NavyDark,
                selectedTextColor = NavyDark,
                indicatorColor = Slate100,
                unselectedIconColor = TextMuted,
                unselectedTextColor = TextMuted
            ),
            modifier = Modifier.testTag("nav_vip_tips")
        )

        // 3. Settings
        val isSettingsSelected = currentSection == AppSection.SETTINGS
        NavigationBarItem(
            selected = isSettingsSelected,
            onClick = { onSectionSelected(AppSection.SETTINGS) },
            icon = {
                Icon(
                    imageVector = if (isSettingsSelected) Icons.Filled.Settings else Icons.Outlined.Settings,
                    contentDescription = Strings.navSettings(language),
                    modifier = Modifier.size(20.dp)
                )
            },
            label = {
                Text(
                    text = Strings.navSettings(language),
                    fontSize = 11.sp,
                    fontWeight = if (isSettingsSelected) FontWeight.Bold else FontWeight.Medium
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = NavyDark,
                selectedTextColor = NavyDark,
                indicatorColor = Slate100,
                unselectedIconColor = TextMuted,
                unselectedTextColor = TextMuted
            ),
            modifier = Modifier.testTag("nav_settings")
        )
    }
}
