package com.advaitvedant.login
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
class LoginViewModel @Inject constructor(
    val auth: AuthRepository
) : ViewModel() {
    fun login(
        name: String,
        onLoginCompleted: () -> Unit,
        onLoginFailed: () -> Unit
    ){
        viewModelScope.launch {
            if (auth.login(name)) {
                onLoginCompleted()
            } else {
                onLoginFailed()
            }
        }
    }
}

private const val NAME_VALIDATION_REGEX = "^[a-zA-Z]+$"

class NameState(val name: String? = null) :
    TextFieldState(validator = ::isNameValid, errorFor = ::nameValidationError) {
    val doesNameExist = mutableStateOf(true)
    override fun onValueChange(text: String) {
        doesNameExist.value = true
        super.onValueChange(text)
    }
    override val isValid: Boolean
        get() = super.isValid && doesNameExist.value

    override fun getError(): String? {
        return if (!doesNameExist.value) "Name does not exist" else super.getError()
    }
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

fun nameStateSaver(state: NameState) = listSaver<NameState, Any>(
    save = { listOf(it.text, it.isFocusedDirty, it.doesNameExist.value) },
    restore = {
        state.apply {
            text = it[0] as String
            isFocusedDirty = it[1] as Boolean
            doesNameExist.value = it[2] as Boolean
        }
    }
)

val NameStateSaver = nameStateSaver(NameState())

