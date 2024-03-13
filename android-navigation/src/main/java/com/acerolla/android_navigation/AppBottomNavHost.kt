package com.acerolla.android_navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.acerolla.shared_resources.SharedResources
import com.acerolla.ui_android.navigation.ADD_THING_NAV_GRAPH_PATTERN
import com.acerolla.ui_android.navigation.PROFILE_NAV_GRAPH_PATTERN
import com.acerolla.ui_android.navigation.THINGS_NAV_GRAPH_PATTERN
import com.acerolla.ui_android.navigation.addThingGraph
import com.acerolla.ui_android.navigation.profileGraph
import com.acerolla.ui_android.navigation.thingsGraph

internal const val APP_BOTTOM_NAV_HOST_SCREEN = "APP_BOTTOM_NAV_HOST_SCREEN"

@Composable
fun AppBottomNavHost(
    startDestination: String = THINGS_NAV_GRAPH_PATTERN,
    onScreenObjectDetailsClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavMenu(navController) }
    ) { innerPadding ->
        NavHost(navController, startDestination = startDestination, Modifier.padding(innerPadding)) {
            thingsGraph(
                onWatchMoreClick = onScreenObjectDetailsClick
            )
            addThingGraph(
                navController = navController
            )
            profileGraph(
                onExitClick = onLogoutClick
            )
        }
    }
}

@Composable
fun BottomNavMenu(
    navController: NavHostController
) {
    val bottomTabNavGraphs = NavGraph.getBottomTabsGraphs()
    BottomNavigation(
        backgroundColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomTabNavGraphs.forEach { navGraph ->
            BottomNavigationItem(
                icon = { getBottomNavTabIconForGraph(navGraph.route) },
                label = { Text(getBottomNavTabTitleForGraph(navGraph.route)) },
                selected = currentDestination?.hierarchy?.any { it.route == navGraph.route } == true,
                onClick = {
                    navController.navigate(navGraph.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                            inclusive = true
                        }
                    }
                },
                selectedContentColor = Color(SharedResources.colors.primary.getColor(LocalContext.current)),
            )
        }
    }
}

private fun getBottomNavTabIconForGraph(graphName: String) = when(graphName) {
    ADD_THING_NAV_GRAPH_PATTERN -> Icons.Outlined.Add
    THINGS_NAV_GRAPH_PATTERN -> Icons.Outlined.Home
    PROFILE_NAV_GRAPH_PATTERN -> Icons.Outlined.AccountBox
    else -> throw IllegalStateException("Can not get icon for graph $graphName")
}

private fun getBottomNavTabTitleForGraph(graphName: String) = when(graphName) {
    ADD_THING_NAV_GRAPH_PATTERN -> "Добавить"
    THINGS_NAV_GRAPH_PATTERN -> "Главная"
    PROFILE_NAV_GRAPH_PATTERN -> "Профиль"
    else -> throw IllegalStateException("Can not get title for graph $graphName")
}