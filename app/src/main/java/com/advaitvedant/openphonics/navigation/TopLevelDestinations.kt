package com.advaitvedant.openphonics.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.advaitvedant.design.icon.OpIcons
import com.advaitvedant.openphonics.R

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    HOME(
        selectedIcon = OpIcons.Home,
        unselectedIcon = OpIcons.HomeBorder,
        iconTextId = R.string.home,
        titleTextId = R.string.app_name,
    )
}