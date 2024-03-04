package com.acerolla.android_navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.acerolla.ui_android.navigation.STREET_OBJECT_INFO_SCREEN_PATTERN
import com.acerolla.ui_android.navigation.streetObjectDetailsScreen

internal const val MAIN_APP_NAV_GRAPH = "MAIN_APP_NAV_GRAPH"

fun NavGraphBuilder.mainAppGraph(
    navController: NavController,
    onLogoutClick: () -> Unit
) {
    navigation(
        startDestination = APP_BOTTOM_NAV_HOST_SCREEN,
        route = MAIN_APP_NAV_GRAPH
    ) {
        bottomNavHostScreen(
            onScreenObjectDetailsClick = {
                navController.navigate(STREET_OBJECT_INFO_SCREEN_PATTERN)
            },
            onLogoutClick = onLogoutClick
        )
        streetObjectDetailsScreen(
            onBackBtnClick = {
                navController.popBackStack()
            }
        )
    }
}

internal fun NavGraphBuilder.bottomNavHostScreen(
    onScreenObjectDetailsClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    composable(APP_BOTTOM_NAV_HOST_SCREEN) {
        AppBottomNavHost(
            onScreenObjectDetailsClick = onScreenObjectDetailsClick,
            onLogoutClick = onLogoutClick
        )
    }
}
