package com.acerolla.android_navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

val ThingsNavGraphPattern = "thingsNavGraph"
private val rootScreenPattern = "rootScreen"

fun NavGraphBuilder.thingsGraph(
    onExitClick: () -> Unit
) {
    navigation(
        startDestination = rootScreenPattern,
        route = ThingsNavGraphPattern
    ) {
        rootScreen(onExitClick)
    }
}

internal fun NavGraphBuilder.rootScreen(
    onExitClick: () -> Unit
) {
    composable(rootScreenPattern) {
        ThingsRootScreen {
            onExitClick()
        }
    }
}