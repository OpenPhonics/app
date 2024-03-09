package com.advaitvedant.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val signUpRoute = "signUp_route"

fun NavController.navigateToSignUp(navOptions: NavOptions? = null) {
    this.navigate(signUpRoute, navOptions)
}

fun NavGraphBuilder.signUpScreen(onNavigateToLogin: () -> Unit, onSignUpComplete: () -> Unit) {
    composable(route = signUpRoute) {
        SignUpRoute(onSignupComplete = onSignUpComplete, onNavigateToLogin = onNavigateToLogin)
    }
}
