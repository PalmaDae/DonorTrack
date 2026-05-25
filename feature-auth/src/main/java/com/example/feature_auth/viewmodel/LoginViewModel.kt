package com.example.feature_auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ServiceLocator
import com.example.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: UserRepository = ServiceLocator.getUserRepository()
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun login(loginText: String, passwordText: String, onLoginSuccess: () -> Unit) {
        if (loginText.isBlank() || passwordText.isBlank()) {
            _errorMessage.value = "Заполните все поля"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            userRepository.loginUser(loginText, passwordText)
                .onSuccess {
                    _isLoading.value = false
                    onLoginSuccess()
                }
                .onFailure { error ->
                    _isLoading.value = false
                    _errorMessage.value = error.message ?: "Неверный логин или пароль"
                }
        }
    }

    fun clearError() { _errorMessage.value = null }
}