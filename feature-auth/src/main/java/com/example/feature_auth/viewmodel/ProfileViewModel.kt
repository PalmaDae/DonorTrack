package com.example.feature_auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ServiceLocator
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
            android.util.Log.d("ProfileVM", "-> loadProfile() запущен")



            if (!_profileUiState.value.isInitialized) {
                _profileUiState.update { it.copy(isLoading = true, error = null) }
            }

            userRepository.getProfile()
                .onSuccess { user ->
                    android.util.Log.d("ProfileVM", "-> Успех! Данные получены с бэка: City=${user.city}, Blood=${user.bloodType}")

                    _profileUiState.update {
                        it.copy(
                            isLoading = false,
                            isInitialized = true,
                            userModel = user,
                            donations = user.donations,
                            error = null
                        )
                    }
                }
                .onFailure { error ->
                    android.util.Log.e("ProfileVM", "-> Ошибка загрузки профиля!", error)
                    _profileUiState.update {
                        it.copy(
                            isLoading = false,
                            error = error.localizedMessage ?: "Неизвестная ошибка"
                        )
                    }
                }
        }
    }


    private fun performUpdate(action: suspend () -> Result<*>) {
        viewModelScope.launch {
            _profileUiState.update { it.copy(isLoading = true, error = null) }
            action()
                .onSuccess {

                    loadProfile()
                }
                .onFailure { error ->
                    _profileUiState.update { it.copy(isLoading = false, error = error.message) }
                }
        }
    }


    fun updateCity(newCity: String) = performUpdate { userRepository.changeCity(newCity) }

    fun updateBloodType(newBlood: String) = performUpdate { userRepository.changeBloodType(newBlood) }
}