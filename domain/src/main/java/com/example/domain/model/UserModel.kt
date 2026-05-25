package com.example.domain.model

import com.example.domain.model.donation.DonationModel

data class UserModel(
    val name: String = "Your Name",
    val login: String = "",
    val email: String = "",
    val bloodType: BloodType = BloodType.A_PLUS,
    val city: String? = null,
    val hashPass: String = "",
    val bages: List<Bage> = emptyList(),
    val donations: List<DonationModel> = emptyList()
)