package com.advaitvedant.openphonics.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.advaitvedant.bookmarks.bookmarksScreen
import com.advaitvedant.explore.exploreScreen
import com.advaitvedant.home.homeRoute
import com.advaitvedant.home.homeScreen
import com.advaitvedant.openphonics.ui.OpAppState

@Composable
fun OpNavHost(
    appState: OpAppState,
    modifier: Modifier = Modifier,
    startDestination: String = homeRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen({})
        exploreScreen()
        bookmarksScreen()
    }
}