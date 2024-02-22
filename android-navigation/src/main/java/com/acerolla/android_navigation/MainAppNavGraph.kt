package com.acerolla.android_navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

internal const val MAIN_APP_NAV_GRAPH = "MAIN_APP_NAV_GRAPH"

fun NavGraphBuilder.mainAppGraph(
    onLogoutClick: () -> Unit
) {
    navigation(
        startDestination = APP_BOTTOM_NAV_HOST_SCREEN,
        route = MAIN_APP_NAV_GRAPH
    ) {
        bottomNavHostScreen(
            onLogoutClick = onLogoutClick
        )
    }
}

internal fun NavGraphBuilder.bottomNavHostScreen(
    onLogoutClick: () -> Unit
) {
    composable(APP_BOTTOM_NAV_HOST_SCREEN) {
        AppBottomNavHost(
            onLogoutClick = onLogoutClick
        )
    }
}
