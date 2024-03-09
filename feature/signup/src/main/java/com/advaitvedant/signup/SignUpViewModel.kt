package com.advaitvedant.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.advaitvedant.ui.TextFieldState
import com.advaitvedant.ui.textFieldStateSaver
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    fun signUp(
        name: String,
        onSignUpComplete: () -> Unit,
    ){
        onSignUpComplete()
    }

    fun isNameAlreadyUsed(name: String) = true
}

private const val NAME_VALIDATION_REGEX = "^[a-zA-Z]+$"

class NameState(name: String? = null) : TextFieldState(validator = ::isNameValid, errorFor = ::nameValidationError){

    val isNameAlreadyUsed = mutableStateOf(false)

    override fun onValueChange(text: String) {
        isNameAlreadyUsed.value = false
        super.onValueChange(text)
    }
    override val isValid: Boolean
        get() = super.isValid && !isNameAlreadyUsed.value

    override fun getError(): String? {
        return if (isNameAlreadyUsed.value) "Name is not available" else super.getError()
    }
    init {
        name?.let {
            text = it
        }
    }


}
private fun isNameValid(name: String): Boolean {
    return Pattern.matches(NAME_VALIDATION_REGEX, name)
}
/**
 * Returns an error to be displayed or null if no error was found
 */
private fun nameValidationError(name: String): String {
    return "Invalid name: $name"
}



val NameStateSaver = textFieldStateSaver(NameState())
