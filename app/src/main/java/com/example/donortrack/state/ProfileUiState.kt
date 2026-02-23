package com.example.donortrack.state

import com.example.donortrack.data.model.Bage
import com.example.donortrack.data.model.Donation
import com.example.donortrack.data.model.User

data class ProfileUiState(
    val user: User = User(),
    val bages: List<Bage> = emptyList(),
    val donations: List<Donation> = emptyList()
)