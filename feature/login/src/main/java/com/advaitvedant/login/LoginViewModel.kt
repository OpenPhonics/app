package com.advaitvedant.login
import androidx.lifecycle.ViewModel
import com.advaitvedant.ui.TextFieldState
import com.advaitvedant.ui.textFieldStateSaver
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    fun login(
        name: String,
        onLoginComplete: () -> Unit,
    ){
        onLoginComplete()
    }
}

private const val NAME_VALIDATION_REGEX = "^[a-zA-Z]+$"

class NameState(val name: String? = null) :
    TextFieldState(validator = ::isNameValid, errorFor = ::nameValidationError) {
    init {
        name?.let {
            text = it
        }
    }
}

/**
 * Returns an error to be displayed or null if no error was found
 */
private fun nameValidationError(name: String): String {
    return "Invalid name: $name"
}

private fun isNameValid(name: String): Boolean {
    return Pattern.matches(NAME_VALIDATION_REGEX, name)
}

val NameStateSaver = textFieldStateSaver(NameState())

