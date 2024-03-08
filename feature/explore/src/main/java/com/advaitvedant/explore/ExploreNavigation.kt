package com.advaitvedant.explore

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val EXPLORE_ROUTE = "explore_route"

fun NavController.navigateToExplore(navOptions: NavOptions? = null) {
    this.navigate(EXPLORE_ROUTE, navOptions)
}

fun NavGraphBuilder.exploreScreen() {
    composable(route = EXPLORE_ROUTE) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            Text("Explore")
        }
    }
}
