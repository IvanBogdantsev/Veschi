package com.acerolla.ui_android.screens.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acerolla.android_design_system.ROUND_CORNER_RADIUS
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.shared_resources.SharedResources

@Composable
fun SignButton(
    modifier: Modifier,
    @DrawableRes iconId: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color(
                    SharedResources.colors.primary2.getColor(LocalContext.current)
                ),
                shape = RoundedCornerShape(ROUND_CORNER_RADIUS)
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(24.dp)
                .padding(start = 3.dp),
            painter = painterResource(
                id = iconId
            ),
            contentDescription = null,
        )
    }
}

@Preview
@Composable
private fun SignButton_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        SignButton(
            modifier = Modifier,
            iconId = SharedResources.images.apple_icon.drawableResId
        ) {

        }
    }
}

@Preview
@Composable
private fun SignButton_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        SignButton(
            modifier = Modifier,
            iconId = SharedResources.images.google_icon.drawableResId
        ) {

        }
    }
}