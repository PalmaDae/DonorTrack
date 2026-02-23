package com.example.donortrack.viewmodel

import androidx.lifecycle.ViewModel
import com.example.donortrack.data.model.BloodType
import com.example.donortrack.data.model.Donation
import com.example.donortrack.state.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {
    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState

    fun nameUpdate(newName: String) {
        _profileUiState.update {
            it.copy(user = it.user.copy(name = newName))
        }
    }

    fun bloodTypeUpdate(newBloodType: BloodType) {
        _profileUiState.update {
            it.copy(user = it.user.copy(bloodType = newBloodType))
        }
    }

    fun addDonation(donation: Donation) {
        _profileUiState.update {
            it.copy(donations = it.donations + donation)
        }
    }

    fun avatarUpdate(uri: String) {
        _profileUiState.update {
            it.copy(user = it.user.copy(avatarUri = uri))
        }
    }
}