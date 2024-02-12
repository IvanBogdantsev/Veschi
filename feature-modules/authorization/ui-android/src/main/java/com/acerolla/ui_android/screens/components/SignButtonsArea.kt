package com.acerolla.ui_android.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.shared_resources.SharedResources

@Composable
fun SignButtonsArea(
    onGoogleBtnClick: () -> Unit,
    onAppleBtnClick: () -> Unit,
) {
    val signButtonModifier = Modifier
        .height(40.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SignButton(
            modifier = signButtonModifier
                .padding(end = 9.dp)
                .weight(1f),
            iconId = SharedResources.images.google_icon.drawableResId,
            onClick = onGoogleBtnClick
        )
        SignButton(
            modifier = signButtonModifier
                .padding(start = 9.dp)
                .weight(1f),
            iconId = SharedResources.images.apple_icon.drawableResId,
            onClick = onAppleBtnClick
        )
    }
}

@Preview
@Composable
private fun SignButtonsArea_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        SignButtonsArea(onGoogleBtnClick = { /*TODO*/ }) {
            
        }
    }
}

@Preview
@Composable
private fun SignButtonsArea_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        SignButtonsArea(onGoogleBtnClick = { /*TODO*/ }) {

        }
    }
}