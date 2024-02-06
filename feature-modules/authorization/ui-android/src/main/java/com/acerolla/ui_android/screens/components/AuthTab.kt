package com.acerolla.ui_android.screens.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.acerolla.android_design_system.HORIZONTAL_PADDING
import com.acerolla.android_design_system.ROUND_CORNER_RADIUS
import com.acerolla.shared_resources.SharedResources

@Composable
fun AuthTab(
    selectedItemIndex: Int,
    items: List<String>,
    modifier: Modifier = Modifier,
    onClick: (index: Int) -> Unit,
) {
    val tabWidth = (LocalConfiguration.current.screenWidthDp.dp - (HORIZONTAL_PADDING * 2)) / 2
    val indicatorOffset: Dp by animateDpAsState(
        targetValue = tabWidth * selectedItemIndex,
        animationSpec = tween(easing = LinearEasing), label = "",
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(ROUND_CORNER_RADIUS))
            .height(intrinsicSize = IntrinsicSize.Min)
            .background(Color(SharedResources.colors.primary.getColor(LocalContext.current)))
            .padding(1.5.dp)
            .clip(RoundedCornerShape(ROUND_CORNER_RADIUS))
            .background(Color.White)
    ) {
        AuthTabIndicator(
            indicatorWidth = tabWidth,
            indicatorOffset = indicatorOffset,
            indicatorColor = Color(
                SharedResources.colors.primary.getColor(LocalContext.current)
            ),
        )
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            items.mapIndexed { index, text ->
                val isSelected = index == selectedItemIndex
                AuthTabItem(
                    isSelected = isSelected,
                    onClick = {
                        onClick(index)
                    },
                    tabWidth = tabWidth,
                    text = text,
                )
            }
        }
    }
}