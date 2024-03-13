package com.acerolla.ui_android.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.acerolla.android_design_system.HORIZONTAL_PADDING
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.android_design_system.stringResource
import com.acerolla.shared_resources.SharedResources

@Composable
fun ProfileScreen(
    onExitAppClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = HORIZONTAL_PADDING),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = SharedResources.strings.profile)
            )
            ProfileBtn(
                title = stringResource(id = SharedResources.strings.profile),
                onClick = {}
            )
        }
    }
}

@Composable
private fun ProfileBtn(
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = title
        )

    }
}

@Preview
@Composable
fun AddThingScreen_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        ProfileScreen({})
    }
}

@Preview
@Composable
fun AddThingScreen_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        ProfileScreen({})
    }
}