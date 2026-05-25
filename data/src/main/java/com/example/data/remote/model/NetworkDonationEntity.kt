package com.example.data.remote.model

data class NetworkDonationEntity(
    val id: Long?,
    val date: String,
    val donationType: String,
    val donationStatus: String,
    val certificate: String?
)