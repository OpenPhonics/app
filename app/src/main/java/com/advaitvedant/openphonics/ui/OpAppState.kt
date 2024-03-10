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
import com.advaitvedant.bookmarks.BOOKMARKS_ROUTE
import com.advaitvedant.bookmarks.navigateToBookmarks
import com.advaitvedant.explore.EXPLORE_ROUTE
import com.advaitvedant.explore.navigateToExplore
import com.advaitvedant.home.HOME_ROUTE
import com.advaitvedant.home.navigateToHome
import com.advaitvedant.openphonics.navigation.TopLevelDestination
import com.advaitvedant.settings.navigateToSettings
import com.advaitvedant.settings.SETTINGS_ROUTE

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
            HOME_ROUTE -> TopLevelDestination.HOME
            EXPLORE_ROUTE -> TopLevelDestination.EXPLORE
            BOOKMARKS_ROUTE -> TopLevelDestination.BOOKMARKS
            SETTINGS_ROUTE -> TopLevelDestination.SETTINGS
            else -> null
        }

    val shouldShowBottomBar: Boolean
        @Composable
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact && currentTopLevelDestination != null

    val shouldShowNavRail: Boolean
        @Composable
        get() = !shouldShowBottomBar && currentTopLevelDestination != null

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.HOME -> navController.navigateToHome(topLevelNavOptions)
            TopLevelDestination.EXPLORE -> navController.navigateToExplore(topLevelNavOptions)
            TopLevelDestination.BOOKMARKS -> navController.navigateToBookmarks(topLevelNavOptions)
            TopLevelDestination.SETTINGS -> navController.navigateToSettings(topLevelNavOptions)
        }
    }
}
