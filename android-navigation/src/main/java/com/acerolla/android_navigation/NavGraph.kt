package com.acerolla.android_navigation

import com.acerolla.ui_android.navigation.ADD_THING_NAV_GRAPH_PATTERN
import com.acerolla.ui_android.navigation.PROFILE_NAV_GRAPH_PATTERN
import com.acerolla.ui_android.navigation.THINGS_NAV_GRAPH_PATTERN

sealed class NavGraph(
    val route: String
) {
    data object ThingsNavGraph: NavGraph(THINGS_NAV_GRAPH_PATTERN)
    data object ProfileNavGraph: NavGraph(PROFILE_NAV_GRAPH_PATTERN)
    data object AddThingNavGraph: NavGraph(ADD_THING_NAV_GRAPH_PATTERN)

    companion object {

        fun getBottomTabsGraphs(): List<NavGraph> = listOf(
            ThingsNavGraph,
            AddThingNavGraph,
            ProfileNavGraph
        )
    }
}