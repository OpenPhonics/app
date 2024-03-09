package com.advaitvedant.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.advaitvedant.ui.TextInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpRoute(
    onSignupComplete: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Create an Account",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                },
            )
        },
        content = { contentPadding ->
            SignUpScreen(
                contentPadding = contentPadding,
                onNavigateToLogin = onNavigateToLogin,
                onSubmit = { name ->
                    viewModel.signUp(name, onSignupComplete)
                },
                isNameAlreadyUsed = viewModel::isNameAlreadyUsed
            )
        }
    )
}
@Composable
internal fun SignUpScreen(
    contentPadding: PaddingValues,
    onSubmit: (name: String) -> Unit,
    onNavigateToLogin: () -> Unit,
    isNameAlreadyUsed: (String) -> Boolean
){
    Column(
        modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        val focusRequester = remember { FocusRequester() }
        val nameState = remember { NameState() }
        TextInput(
            label = stringResource(id = com.advaitvedant.ui.R.string.name),
            textState = nameState,
            onImeAction = { focusRequester.requestFocus() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        val submit = {
            nameState.isNameAlreadyUsed.value = isNameAlreadyUsed(nameState.text)
            if (nameState.isValid) {
                onSubmit(nameState.text)
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
        OutlinedButton(
            onClick = onNavigateToLogin,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
        ) {
            Text(
                text = "Log in"
            )
        }
    }
}
