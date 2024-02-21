package com.acerolla.android_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.acerolla.ui_android.navigation.ADD_THING_NAV_GRAPH_PATTERN
import com.acerolla.ui_android.navigation.AUTH_NAV_GRAPH_PATTERN
import com.acerolla.ui_android.navigation.PROFILE_NAV_GRAPH_PATTERN
import com.acerolla.ui_android.navigation.THINGS_NAV_GRAPH_PATTERN
import com.acerolla.ui_android.navigation.authGraph

@Composable
fun RootNavHost(
    startDestination: String = AUTH_NAV_GRAPH_PATTERN
) {
    val rootNavController = rememberNavController()
    NavHost(
        navController = rootNavController,
        startDestination = startDestination,
    ) {
        authGraph(
            navController = rootNavController,
            signSuccessed = {
                rootNavController.navigate(APP_BOTTOM_NAV_HOST_SCREEN)
            }
        )
        composable(APP_BOTTOM_NAV_HOST_SCREEN) {
            AppBottomNavHost()
        }
    }
}

internal fun NavController.navigateToAuthGraph(navOptions: NavOptions? = null) {
    this.navigate(AUTH_NAV_GRAPH_PATTERN, navOptions)
}

internal fun NavController.navigateToThingsGraph(navOptions: NavOptions? = null) {
    this.navigate(THINGS_NAV_GRAPH_PATTERN, navOptions)
}

internal fun NavController.navigateToProfileGraph(navOptions: NavOptions? = null) {
    this.navigate(PROFILE_NAV_GRAPH_PATTERN, navOptions)
}

internal fun NavController.navigateToAddThingGraph(navOptions: NavOptions? = null) {
    this.navigate(ADD_THING_NAV_GRAPH_PATTERN, navOptions)
}