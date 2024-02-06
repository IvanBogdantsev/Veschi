package com.acerolla.ui_android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acerolla.android_design_system.HORIZONTAL_PADDING
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.ui_android.screens.components.AuthTab

@Composable
fun AuthRootScreen() {
    val (selected, setSelected) = remember {
        mutableIntStateOf(0)
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 10.dp)
                .padding(horizontal = HORIZONTAL_PADDING),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthTab(
                items = listOf("Вход", "Регистрация"),
                selectedItemIndex = selected,
                onClick = setSelected,
            )
            if (selected == 0) {
                SignInScreen()
            } else {
                SignUpScreen()
            }
        }
    }
}

@Preview
@Composable
fun AuthRootScreen_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        AuthRootScreen()
    }
}

@Preview
@Composable
fun AuthRootScreen_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        AuthRootScreen()
    }
}