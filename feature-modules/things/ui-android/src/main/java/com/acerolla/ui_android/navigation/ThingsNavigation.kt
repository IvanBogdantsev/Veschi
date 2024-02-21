package com.acerolla.ui_android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.acerolla.ui_android.screens.ThingsScreen

const val THINGS_NAV_GRAPH_PATTERN = "THINGS_NAV_GRAPH_PATTERN"
internal const val THINGS_SCREEN_PATTERN = "THINGS_SCREEN_PATTERN"

fun NavGraphBuilder.thingsGraph(
    onExitClick: () -> Unit
) {
    navigation(
        startDestination = THINGS_SCREEN_PATTERN,
        route = THINGS_NAV_GRAPH_PATTERN
    ) {
        rootScreen(onExitClick)
    }
}

internal fun NavGraphBuilder.rootScreen(
    onExitClick: () -> Unit
) {
    composable(THINGS_SCREEN_PATTERN) {
        ThingsScreen {
            onExitClick()
        }
    }
}