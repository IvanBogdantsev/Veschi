package com.acerolla.ui_android.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.acerolla.ui_android.screens.AuthRootScreen

val AUTH_NAV_GRAPH_PATTERN = "authNavGraph"
private val AUTH_ROOT_SCREEN_PATTERN = "AUTH_ROOT_SCREEN_PATTERN"

fun NavGraphBuilder.authGraph(
    navController: NavController,
    signSuccessed: () -> Unit
) {
    navigation(
        startDestination = AUTH_ROOT_SCREEN_PATTERN,
        route = AUTH_NAV_GRAPH_PATTERN
    ) {
        authRootScreen(
            signSuccessed = signSuccessed
        )
    }
}

internal fun NavGraphBuilder.authRootScreen(
    signSuccessed: () -> Unit
) {
    composable(AUTH_ROOT_SCREEN_PATTERN) {
        AuthRootScreen(
            onSignSuccessed = signSuccessed
        )
    }
}