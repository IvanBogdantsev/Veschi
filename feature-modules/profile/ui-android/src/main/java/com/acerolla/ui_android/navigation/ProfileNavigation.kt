package com.acerolla.ui_android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.acerolla.ui_android.screens.ProfileScreen

const val PROFILE_NAV_GRAPH_PATTERN = "PROFILE_NAV_GRAPH_PATTERN"
internal const val PROFILE_SCREEN_PATTERN = "PROFILE_SCREEN_PATTERN"

fun NavGraphBuilder.profileGraph() {
    navigation(
        startDestination = PROFILE_SCREEN_PATTERN,
        route = PROFILE_NAV_GRAPH_PATTERN
    ) {
        profileScreen()
    }
}

internal fun NavGraphBuilder.profileScreen() {
    composable(PROFILE_SCREEN_PATTERN) {
        ProfileScreen()
    }
}