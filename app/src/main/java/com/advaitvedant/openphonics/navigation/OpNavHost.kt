package com.advaitvedant.openphonics.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.advaitvedant.home.homeRoute
import com.advaitvedant.home.homeScreen
import com.advaitvedant.home.navigateToHome
import com.advaitvedant.login.loginScreen
import com.advaitvedant.login.navigateToLogin
import com.advaitvedant.openphonics.ui.OpAppState
import com.advaitvedant.skill.navigateToSkill
import com.advaitvedant.skill.skillScreen
import kotlinx.coroutines.flow.first

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

        skillScreen(navController::navigateToHome)
        homeScreen(navController::navigateToLogin,  navController::navigateToSkill)
        loginScreen(navController::navigateToHome, navController::navigateToHome)
    }
}