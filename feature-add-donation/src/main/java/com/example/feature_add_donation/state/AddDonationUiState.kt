package com.example.feature_add_donation.state

data class AddDonationUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)