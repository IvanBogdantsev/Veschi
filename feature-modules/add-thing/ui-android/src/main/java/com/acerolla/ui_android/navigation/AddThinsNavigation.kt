package com.acerolla.ui_android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.acerolla.ui_android.screens.AddThingScreen

const val ADD_THING_NAV_GRAPH_PATTERN = "ADD_THING_NAV_GRAPH_PATTERN"
internal const val ADD_THING_SCREEN_PATTERN = "ADD_THING_SCREEN_PATTERN"

fun NavGraphBuilder.addThingGraph() {
    navigation(
        startDestination = ADD_THING_SCREEN_PATTERN,
        route = ADD_THING_NAV_GRAPH_PATTERN
    ) {
        thingsScreen()
    }
}

internal fun NavGraphBuilder.thingsScreen() {
    composable(ADD_THING_SCREEN_PATTERN) {
        AddThingScreen()
    }
}