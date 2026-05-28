package com.example.data.remote.model

data class ProfileDTO(
    val username: String?,
    val email: String?,
    val bloodType: String?,
    val city: String?,
    val donations: List<NetworkDonationEntity>?
)

data class CityChangeDto(
    val city: String
)

data class BloodTypeChangeDto(
    val bloodType: String
)

data class EmailChangeDto(
    val newEmail: String
)

data class PasswordChangeDto(
    val oldPassword: String,
    val newPassword: String,
    val confirmPassword: String
)