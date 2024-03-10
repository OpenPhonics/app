package com.advaitvedant.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advaitevdant.data.repository.AuthRepository
import com.advaitevdant.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val auth: AuthRepository,
    private val userData: UserDataRepository
) : ViewModel() {
    fun logout(){
        auth.logout()
    }
    val darkTheme = userData.userData.map {it.isDarkTheme}
    fun switchDarkTheme(darkMode: Boolean){
        viewModelScope.launch {
            userData.setDarkTheme(darkMode)
        }
    }
}