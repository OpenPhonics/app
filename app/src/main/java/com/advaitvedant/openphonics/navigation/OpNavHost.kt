package com.advaitvedant.openphonics.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.advaitvedant.openphonics.ui.OpAppState

@Composable
fun OpNavHost(
    appState: OpAppState,
    modifier: Modifier = Modifier,
    startDestination: String = "",
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {

    }
}