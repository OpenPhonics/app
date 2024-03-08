package com.advaitvedant.openphonics.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.advaitvedant.design.icon.OpIcons
import com.advaitvedant.openphonics.R

enum class TopLevelDestination(
    val icon: ImageVector,
    val text: Int,
) {
    HOME(
        icon = OpIcons.Home,
        text = R.string.home,
    ),
    EXPLORE(
        icon = OpIcons.Explore,
        text = R.string.explore
    ),
    BOOKMARKS(
        icon = OpIcons.Bookmark,
        text = R.string.bookmarks
    )
    
}