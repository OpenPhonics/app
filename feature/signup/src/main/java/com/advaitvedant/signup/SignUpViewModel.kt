package com.advaitvedant.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advaitevdant.data.repository.AuthRepository
import com.advaitvedant.ui.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    val auth: AuthRepository
) : ViewModel() {
    fun signUp(
        name: String,
        onSignUpCompleted: () -> Unit,
        onSignUpFailed: () -> Unit
    ){
        viewModelScope.launch {
            if (auth.signup(name)) {
                onSignUpCompleted()
            } else {
                onSignUpFailed()
            }
        }
    }
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

fun nameStateSaver(state: NameState) = listSaver<NameState, Any>(
    save = { listOf(it.text, it.isFocusedDirty, it.isNameAlreadyUsed.value) },
    restore = {
        state.apply {
            text = it[0] as String
            isFocusedDirty = it[1] as Boolean
            isNameAlreadyUsed.value = it[2] as Boolean
        }
    }
)

val NameStateSaver = nameStateSaver(NameState())
