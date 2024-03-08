package com.advaitvedant.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            TextButton(onClick=onSignUpComplete){
                Text("signUp")
            }
            TextButton(onClick=onNavigateToLogin){
                Text("go to login")
            }
        }
    }
}
