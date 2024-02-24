package com.advaitvedant.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advaitvedant.common.model.Skill
import com.advaitvedant.data.repository.AuthRepository
import com.advaitvedant.data.repository.SkillRepository
import com.advaitvedant.data.utils.SyncManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val auth: AuthRepository,
    skill: SkillRepository,
    syncManager: SyncManager,
    ) : ViewModel()
{
    val isSyncing = syncManager.isSyncing
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )
    val isLoggedIn = auth.isLoggedIn
    fun logout(){
        auth.logout()
    }
    val skillState: StateFlow<SkillUiState> = skill.all()
        .map(SkillUiState::Success)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SkillUiState.Loading,
        )
}

sealed interface SkillUiState {
    data object Loading : SkillUiState

    data class Success(
        val skills: List<Skill>,
    ) : SkillUiState
}