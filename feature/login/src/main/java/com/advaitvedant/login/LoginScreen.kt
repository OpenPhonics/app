package com.advaitvedant.login

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
import com.advaitvedant.ui.TextInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginRoute(
    onLoginComplete: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Welcome Back",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                },
                )
        },
        content = { contentPadding ->
            LoginScreen(
                contentPadding = contentPadding,
                onNavigateToSignUp = onNavigateToSignUp,
                onSubmit = { name ->
                    viewModel.login(name, onLoginComplete)
                }
            )
        }
    )
}
@Composable
internal fun LoginScreen(
    contentPadding: PaddingValues,
    onSubmit: (name: String) -> Unit,
    onNavigateToSignUp: () -> Unit
){
    Column(
        modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        val focusRequester = remember { FocusRequester() }
        val nameState by rememberSaveable(stateSaver = NameStateSaver){
            mutableStateOf(NameState())
        }
        TextInput(
            label = stringResource(id = com.advaitvedant.ui.R.string.name),
            textState = nameState,
            onImeAction = { focusRequester.requestFocus() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        val submit = {
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
                text = stringResource(R.string.login)
            )
        }
        OutlinedButton(
            onClick = onNavigateToSignUp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
        ) {
            Text(
                text = "Sign up"
            )
        }
    }
}
