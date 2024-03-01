package com.acerolla.android_navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.acerolla.api.AuthStatusChecker
import com.acerolla.ui_android.navigation.AUTH_NAV_GRAPH_PATTERN
import com.acerolla.ui_android.navigation.authGraph
import org.koin.compose.koinInject

@Composable
fun RootNavHost() {
    val rootNavController = rememberNavController()
    val authStatusChecker = koinInject<AuthStatusChecker>()
    var isUserAuthorized: Boolean? by remember { mutableStateOf(null) }
    var destination by remember { mutableStateOf(SPLASH_SCREEN_PATTERN) }
    LaunchedEffect(Unit) {
        isUserAuthorized = authStatusChecker.isUserAuthorized()?.authorized
        destination = when(isUserAuthorized) {
            true -> MAIN_APP_NAV_GRAPH
            false -> AUTH_NAV_GRAPH_PATTERN
            else -> SPLASH_SCREEN_PATTERN
        }
    }
    NavHost(
        navController = rootNavController,
        startDestination = destination,
    ) {
        authGraph(
            navController = rootNavController,
            signSuccessed = {
                rootNavController.navigateToApp()
            }
        )
        mainAppGraph(
            navController = rootNavController,
            onLogoutClick = {
                rootNavController.navigateToAuthGraph()
            }
        )
        composable(SPLASH_SCREEN_PATTERN) {
            SplashScreen()
        }
    }
}

internal fun NavController.navigateToAuthGraph(navOptions: NavOptions? = null) {
    this.navigate(AUTH_NAV_GRAPH_PATTERN, navOptions)
}

internal fun NavController.navigateToApp(navOptions: NavOptions? = null) {
    this.navigate(APP_BOTTOM_NAV_HOST_SCREEN, navOptions)
}