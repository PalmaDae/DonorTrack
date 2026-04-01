package com.example.feature_auth.viewmodel

import com.example.donortrack.data.model.UserModel
import com.example.donortrack.state.RegistrationUiState
import com.example.donortrack.util.ServiceLocator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegistrationVIewModel {
    private val _registrationUiState = MutableStateFlow(RegistrationUiState())
    val registrationUiState: StateFlow<RegistrationUiState> = _registrationUiState

    suspend fun registerUser(userModel: UserModel) {
        ServiceLocator.getUserRepository().createNewUser(userModel)
    }
}