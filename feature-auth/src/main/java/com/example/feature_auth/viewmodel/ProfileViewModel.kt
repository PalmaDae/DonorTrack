package com.example.feature_auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ServiceLocator
import com.example.data.remote.model.PasswordChangeDto
import com.example.data.repository.UserRepository
import com.example.feature_auth.state.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userRepository: UserRepository = ServiceLocator.getUserRepository()
) : ViewModel() {

    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()

    init {
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {
            _profileUiState.update { it.copy(isLoading = true, error = null) }
            userRepository.getProfile()
                .onSuccess { user ->
                    _profileUiState.update { it.copy(isLoading = false, userModel = user, donations = user.donations) }
                }
                .onFailure { error ->
                    _profileUiState.update { it.copy(isLoading = false, error = error.message) }
                }
        }
    }

    private fun performUpdate(action: suspend () -> Result<*>) {
        viewModelScope.launch {
            _profileUiState.update { it.copy(isLoading = true) }
            action()
                .onSuccess { loadProfile() }
                .onFailure { error -> _profileUiState.update { it.copy(isLoading = false, error = error.message) } }
        }
    }

    fun updateCity(newCity: String) = performUpdate { userRepository.changeCity(newCity) }

    fun updateBloodType(newBlood: String) = performUpdate { userRepository.changeBloodType(newBlood) }

    fun updateEmail(newEmail: String) = performUpdate { userRepository.changeEmail(newEmail) }

    fun updatePassword(old: String, new: String, confirm: String) = performUpdate {
        userRepository.changePassword(PasswordChangeDto(old, new, confirm))
    }
}