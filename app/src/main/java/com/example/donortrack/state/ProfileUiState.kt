package com.example.donortrack.state

import com.example.donortrack.data.model.Bage
import com.example.donortrack.data.model.Donation
import com.example.donortrack.data.model.UserModel

data class ProfileUiState(
    val userModel: UserModel = UserModel(),
    val bages: List<Bage> = emptyList(),
    val donations: List<Donation> = emptyList()
)