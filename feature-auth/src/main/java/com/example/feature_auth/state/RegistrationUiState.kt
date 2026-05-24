package com.example.feature_auth.state

data class RegistrationUiState(
    val isLoading: Boolean = false,
    val isCodeSent: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null
)