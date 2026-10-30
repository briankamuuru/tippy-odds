package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BorderLight
import com.example.ui.theme.NavyDark
import com.example.ui.theme.OrangeBorder
import com.example.ui.theme.OrangeContainer
import com.example.ui.theme.OrangeLight
import com.example.ui.theme.OrangePrimary
import com.example.ui.theme.PureWhite
import com.example.ui.theme.Slate100
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun SegmentedTabControl(
    tabs: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    useOrangeStyle: Boolean = true,
    modifier: Modifier = Modifier
) {
    val containerBg = if (useOrangeStyle) OrangeLight else Slate100
    val containerBorder = if (useOrangeStyle) OrangeBorder else BorderLight

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(containerBg)
            .border(width = 1.2.dp, color = containerBorder, shape = RoundedCornerShape(8.dp))
            .padding(3.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = index == selectedIndex
                val tabBg = when {
                    isSelected && useOrangeStyle -> PureWhite
                    isSelected -> PureWhite
                    else -> Color.Transparent
                }
                val textColor = when {
                    isSelected && useOrangeStyle -> OrangePrimary
                    isSelected -> NavyDark
                    else -> TextSecondary
                }
                val tabBorderModifier = if (isSelected && useOrangeStyle) {
                    Modifier.border(width = 1.dp, color = OrangeBorder, shape = RoundedCornerShape(6.dp))
                } else {
                    Modifier
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(34.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .then(tabBorderModifier)
                        .background(tabBg)
                        .clickable { onTabSelected(index) }
                        .padding(horizontal = 4.dp)
                        .testTag("tab_item_$index"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 11.5.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = textColor,
                            textAlign = TextAlign.Center
                        ),
                        maxLines = 1
                    )
                }
            }
        }
    }
}
