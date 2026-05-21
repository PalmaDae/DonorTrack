package com.example.feature_add_donation.state

import com.example.domain.model.donation.DonationType

data class AddDonationUiState(
    val date: String = "",
    val type: DonationType = DonationType.BLOOD,
    val certificateUri: String? = null,
    val loading: Boolean = false
)