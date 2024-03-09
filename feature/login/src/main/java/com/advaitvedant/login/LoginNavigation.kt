package com.advaitvedant.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val loginRoute = "login_route"

fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    this.navigate(loginRoute, navOptions)
}

fun NavGraphBuilder.loginScreen(onNavigateToSignup: () -> Unit, onLoginComplete: () -> Unit) {
    composable(route = loginRoute) {
        LoginRoute(
            onLoginComplete = onLoginComplete,
            onNavigateToSignUp = onNavigateToSignup
        )
    }
}
