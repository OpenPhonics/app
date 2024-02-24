@file:JvmName("LoginScreenKt")

package com.advaitvedant.login

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.advaitvedant.design.supportWideScreen
@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToSignUp: () -> Unit,
    onLoginComplete: () -> Unit,
) {
    val username by viewModel.username.collectAsStateWithLifecycle()

    LoginScreen(
        login = {
            viewModel.login(
                onLoginComplete = onLoginComplete,
                onLoginFailed = {
                    viewModel.username.value.usernameDoesNextExist()
                    viewModel.username.value.enableShowErrors()
                }

            )
        },
        username = username,
        onUsernameChanged = viewModel::onUsernameChanged,
        onNavigateToSignUp = onNavigateToSignUp,
    )
}
@Composable
internal fun LoginScreen(
    login: ()->Unit,
    username: UsernameState,
    onNavigateToSignUp: () -> Unit,
    onUsernameChanged: (String) -> Unit
) {
    var showBranding by rememberSaveable { mutableStateOf(true) }

    Scaffold(modifier = Modifier.supportWideScreen()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            AnimatedVisibility(
                showBranding,
                Modifier.fillMaxWidth()
            ) {
                Branding()
            }

            LoginContent(
                onNavigateToSignUp = onNavigateToSignUp,
                login = login,
                onFocusChange = { focused -> showBranding = !focused },
                username = username,
                onUsernameChanged = onUsernameChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }
    }
}

@Composable
private fun Branding(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.wrapContentHeight(align = Alignment.CenterVertically)
    ) {
        Logo(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 76.dp)
        )
        Text(
            text = "Learning made easy",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun Logo(
    modifier: Modifier = Modifier,
    lightTheme: Boolean = LocalContentColor.current.luminance() < 0.5f,
) {
//    val assetId = if (lightTheme) {
//        R.drawable.ic_logo_light
//    } else {
//        R.drawable.ic_logo_dark
//    }
//    Image(
//        painter = painterResource(id = assetId),
//        modifier = modifier,
//        contentDescription = null
//    )
}

@Composable
private fun LoginContent(
    login: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    username: UsernameState,
    onUsernameChanged: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Login or create an account",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 64.dp, bottom = 12.dp)
        )

        onFocusChange(username.isFocused)
        Username(
            username = username,
            onUsernameChanged = onUsernameChanged,
            imeAction = ImeAction.Done,
            onImeAction = login
        )
        Button(
            onClick = login,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp, bottom = 3.dp)
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.titleSmall
            )
        }
        TextButton(
            onClick = onNavigateToSignUp,
        ){
            Text(text = "Signup",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.76f),
                modifier = Modifier.paddingFromBaseline(top = 25.dp)
            )
        }
    }
}


@Composable
fun Username(
    username: TextFieldState,
    onUsernameChanged: (String)->Unit,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {}
) {
    OutlinedTextField(
        value = username.text,
        onValueChange = {
            if (!it.contains("\n")) {
                onUsernameChanged(it)
            }
        },
        label = {
            Text(
                text = "Username",
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                username.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    username.enableShowErrors()
                }
            },
        textStyle = MaterialTheme.typography.bodyMedium,
        isError = username.showErrors(),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = KeyboardType.Ascii
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
            }
        ),
        singleLine = true
    )
    username.getError()?.let { error -> TextFieldError(textError = error) }
}

@Composable
fun TextFieldError(textError: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = textError,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.error
        )
    }
}