package com.advaitvedant.openphonics.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.advaitvedant.openphonics.navigation.TopLevelDestination

@Composable
fun rememberOpAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController(),

    ): OpAppState {
    return remember(
        navController,
        windowSizeClass,
    ) {
        OpAppState(
            navController,
            windowSizeClass,
        )
    }
}

@Stable
class OpAppState(
    val navController: NavHostController,
    val windowSizeClass: WindowSizeClass,
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            // route -> TopLevelDestination.ROUTE
            else -> null
        }

    val shouldShowBottomBar: Boolean
        @Composable
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact && currentTopLevelDestination != null

    val shouldShowNavRail: Boolean
        @Composable
        get() = !shouldShowBottomBar && currentTopLevelDestination != null

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

//        when (topLevelDestination) {
            // TopLevelDestination.ROUTE -> navController.navigateToRoute(topLevelNavOptions)
//        }
    }
}
