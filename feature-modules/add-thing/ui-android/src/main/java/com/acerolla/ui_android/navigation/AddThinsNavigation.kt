package com.acerolla.ui_android.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.acerolla.ui_android.screens.AddMapPointScreen
import com.acerolla.ui_android.screens.AddThingScreen

const val ADD_THING_NAV_GRAPH_PATTERN = "ADD_THING_NAV_GRAPH_PATTERN"
internal const val ADD_THING_SCREEN_PATTERN = "ADD_THING_SCREEN_PATTERN"
internal const val ADD_MAP_POINT_SCREEN_PATTERN = "ADD_MAP_POINT_SCREEN_PATTERN"

fun NavGraphBuilder.addThingGraph(
    navController: NavController
) {
    navigation(
        startDestination = ADD_THING_SCREEN_PATTERN,
        route = ADD_THING_NAV_GRAPH_PATTERN
    ) {
        addThingScreen(
            onAddPointClick = {
                navController.navigate(ADD_MAP_POINT_SCREEN_PATTERN)
            }
        )
        addMapPointScreen(
            onSuccessullyAdded = {
                navController.popBackStack()
            }
        )
    }
}

internal fun NavGraphBuilder.addThingScreen(
    onAddPointClick: () -> Unit
) {
    composable(ADD_THING_SCREEN_PATTERN) {
        AddThingScreen(
            onAddPointClick = onAddPointClick
        )
    }
}

internal fun NavGraphBuilder.addMapPointScreen(
    onSuccessullyAdded: () -> Unit
) {
    composable(ADD_MAP_POINT_SCREEN_PATTERN) {
        AddMapPointScreen(
            onSuccessullyAdded = onSuccessullyAdded
        )
    }
}