package com.example.feature_auth.state

import com.example.domain.model.UserModel
import com.example.domain.model.donation.DonationModel

data class ProfileUiState(
    val isLoading: Boolean = false,
    val isInitialized: Boolean = false,
    val userModel: UserModel = UserModel(),
    val donations: List<DonationModel> = emptyList(),
    val error: String? = null
)