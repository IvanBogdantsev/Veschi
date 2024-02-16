package com.acerolla.android_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.acerolla.ui_android.navigation.AUTH_NAV_GRAPH_PATTERN
import com.acerolla.ui_android.navigation.ThingsNavGraphPattern
import com.acerolla.ui_android.navigation.authGraph
import com.acerolla.ui_android.navigation.thingsGraph

@Composable
fun RootNavHost(startDestination: String = AUTH_NAV_GRAPH_PATTERN) {
    val rootNavController = rememberNavController()
    NavHost(
        navController = rootNavController,
        startDestination = startDestination,
    ) {
        authGraph(
            navController = rootNavController,
            signSuccessed = {
                rootNavController.navigateToThingsGraph()
            }
        )
        thingsGraph {
            rootNavController.navigateToAuthGraph()
        }
    }
}

fun NavController.navigateToAuthGraph(navOptions: NavOptions? = null) {
    this.navigate(AUTH_NAV_GRAPH_PATTERN, navOptions)
}

fun NavController.navigateToThingsGraph(navOptions: NavOptions? = null) {
    this.navigate(ThingsNavGraphPattern, navOptions)
}