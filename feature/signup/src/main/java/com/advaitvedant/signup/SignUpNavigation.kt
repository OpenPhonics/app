package com.advaitvedant.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val SIGNUP_ROUTE = "signUp_route"

fun NavController.navigateToSignUp(navOptions: NavOptions? = null) {
    this.navigate(SIGNUP_ROUTE, navOptions)
}

fun NavGraphBuilder.signUpScreen(onNavigateToLogin: () -> Unit, onSignUpComplete: () -> Unit) {
    composable(route = SIGNUP_ROUTE) {
        SignUpRoute(
            onSignupComplete = onSignUpComplete,
            onNavigateToLogin = onNavigateToLogin
        )
    }
}
