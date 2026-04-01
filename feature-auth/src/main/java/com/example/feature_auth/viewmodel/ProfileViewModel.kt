package com.example.feature_auth.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.model.BloodType
import com.example.domain.model.Donation
import com.example.feature_auth.state.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {
    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState

    fun nameUpdate(newName: String) {
        _profileUiState.update {
            it.copy(userModel = it.userModel.copy(name = newName))
        }
    }

    fun bloodTypeUpdate(newBloodType: BloodType) {
        _profileUiState.update {
            it.copy(userModel = it.userModel.copy(bloodType = newBloodType))
        }
    }

    fun addDonation(donation: Donation) {
        _profileUiState.update {
            it.copy(donations = it.donations + donation)
        }
    }

    fun avatarUpdate(uri: String) {
        _profileUiState.update {
            it.copy(userModel = it.userModel.copy(avatarUri = uri))
        }
    }
}