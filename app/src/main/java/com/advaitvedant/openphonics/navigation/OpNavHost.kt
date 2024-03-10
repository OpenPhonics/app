package com.advaitvedant.openphonics.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.advaitvedant.bookmarks.bookmarksScreen
import com.advaitvedant.explore.exploreScreen
import com.advaitvedant.home.homeScreen
import com.advaitvedant.home.navigateToHome
import com.advaitvedant.lesson.lessonScreen
import com.advaitvedant.lesson.navigateToLesson
import com.advaitvedant.login.LOGIN_ROUTE
import com.advaitvedant.login.loginScreen
import com.advaitvedant.login.navigateToLogin
import com.advaitvedant.openphonics.ui.OpAppState
import com.advaitvedant.settings.settingsScreen
import com.advaitvedant.signup.navigateToSignUp
import com.advaitvedant.signup.signUpScreen

@Composable
fun OpNavHost(
    appState: OpAppState,
    modifier: Modifier = Modifier,
    startDestination: String = LOGIN_ROUTE,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen(navController::navigateToLesson)
        exploreScreen()
        bookmarksScreen()
        settingsScreen()
        loginScreen(navController::navigateToSignUp,navController::navigateToHome)
        signUpScreen(navController::navigateToLogin, navController::navigateToHome)
        lessonScreen(navController::navigateToHome)
    }
}