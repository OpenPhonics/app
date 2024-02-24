package com.advaitvedant.login

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advaitvedant.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val auth: AuthRepository,
) : ViewModel(){
    private val _username = MutableStateFlow(UsernameState())
    val username = _username.asStateFlow()
    fun onUsernameChanged(username: String) {
        _username.value = _username.value.copy(username)
    }
    fun login(
        onLoginComplete: ()->Unit,
        onLoginFailed: ()->Unit
    ) {
        if (!username.value.isValid) {
            onLoginFailed()
            return
        }
        viewModelScope.launch {
            if (auth.login(username.value.text)){
                onLoginComplete()
            } else {
                onLoginFailed()
            }
        }
    }
}
