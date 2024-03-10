package com.advaitvedant.settings

import androidx.lifecycle.ViewModel
import com.advaitevdant.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val auth: AuthRepository
) : ViewModel() {
    fun logout(){
        auth.logout()
    }
}