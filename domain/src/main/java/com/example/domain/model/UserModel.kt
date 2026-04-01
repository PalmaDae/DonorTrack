package com.example.domain.model

data class UserModel(
    val avatarResId: Int? = null,
    val avatarUri: String? = null,
    val name: String = "Your Name",
    val bloodType: BloodType = BloodType.A_PLUS,
    val hashPass: String = "",
    val login: String = "",
    val email: String = "",
    val bages: List<Bage> = emptyList()
)