package com.acerolla.android_navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.acerolla.android_design_system.ThingsAppTheme

internal const val SPLASH_SCREEN_PATTERN =  "SPLASH_SCREEN_PATTERN"

@Composable
fun SplashScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "The Veschi App",
                fontSize = 16.sp
            )
        }
    }
}

@Preview
@Composable
private fun SplashScreen_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        SplashScreen()
    }
}

@Preview
@Composable
private fun SplashScreen_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        SplashScreen()
    }
}