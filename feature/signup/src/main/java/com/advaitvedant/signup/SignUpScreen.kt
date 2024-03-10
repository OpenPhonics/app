package com.advaitvedant.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.advaitvedant.design.component.OpTopAppBar
import com.advaitvedant.design.theme.LocalBorderPadding
import com.advaitvedant.ui.TextInput

@Composable
fun SignUpRoute(
    onSignupComplete: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
){
    Column(modifier = Modifier.fillMaxSize()){
        OpTopAppBar(title = stringResource(R.string.create_an_account))
        SignUpScreen(
            onNavigateToLogin = onNavigateToLogin,
            onSubmit = { name ->
                viewModel.signUp(
                    name = name.text,
                    onSignUpCompleted = onSignupComplete,
                    onSignUpFailed = {name.isNameAlreadyUsed.value = true
                    }
                )
            },
        )
    }
}
@Composable
internal fun SignUpScreen(
    onSubmit: (NameState) -> Unit,
    onNavigateToLogin: () -> Unit,
){
    Column(
        modifier = Modifier
            .padding(LocalBorderPadding.current)
            .fillMaxWidth(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        val focusRequester = remember { FocusRequester() }
        val nameState by rememberSaveable(stateSaver = NameStateSaver) {
            mutableStateOf(NameState())
        }
        TextInput(
            label = stringResource(id = com.advaitvedant.ui.R.string.name),
            textState = nameState,
            onImeAction = { focusRequester.requestFocus() }
        )
        TextButton(onClick = onNavigateToLogin){
            Text(stringResource(R.string.already_have_an_account))
        }
        Spacer(modifier = Modifier.height(16.dp))

        val submit = {
            if (nameState.isValid) {
                onSubmit(nameState)
            }
        }
        Button(
            onClick = submit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            enabled = nameState.isValid
        ) {
            Text(
                text = stringResource(R.string.signup)
            )
        }
    }
}
