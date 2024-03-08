package com.advaitvedant.bookmarks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val bookmarksRoute = "bookmarks_route"

fun NavController.navigateToBookmarks(navOptions: NavOptions? = null) {
    this.navigate(bookmarksRoute, navOptions)
}

fun NavGraphBuilder.bookmarksScreen() {
    composable(route = bookmarksRoute) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            Text("Bookmarks")
        }
    }
}