package com.acerolla.ui_android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.acerolla.ui_android.screens.StreetObjectDetails
import com.acerolla.ui_android.screens.ThingsScreen

const val THINGS_NAV_GRAPH_PATTERN = "THINGS_NAV_GRAPH_PATTERN"
const val STREET_OBJECT_INFO_SCREEN_PATTERN = "STREET_OBJECT_INFO_SCREEN_PATTERN"
internal const val THINGS_SCREEN_PATTERN = "THINGS_SCREEN_PATTERN"

fun NavGraphBuilder.thingsGraph(
    onWatchMoreClick: () -> Unit
) {
    navigation(
        startDestination = THINGS_SCREEN_PATTERN,
        route = THINGS_NAV_GRAPH_PATTERN
    ) {
        rootScreen(
            onWatchMoreClick = onWatchMoreClick
        )
    }
}

internal fun NavGraphBuilder.rootScreen(
    onWatchMoreClick: () -> Unit
) {
    composable(THINGS_SCREEN_PATTERN) {
        ThingsScreen(
            onWatchMoreClick = onWatchMoreClick
        )
    }
}

fun NavGraphBuilder.streetObjectDetailsScreen(
    onBackBtnClick: () -> Unit
) {
    composable(
        route = STREET_OBJECT_INFO_SCREEN_PATTERN
    ) {
        StreetObjectDetails(
            onBackBtnClick = onBackBtnClick
        )
    }
}