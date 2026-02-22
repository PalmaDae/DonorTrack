package com.example.donortrack.state

import com.example.donortrack.data.model.Bage
import com.example.donortrack.data.model.Donation
import com.example.donortrack.data.model.User

data class ProfileUiState(
    val user: User = User(),
    val bages: MutableList<Bage> = mutableListOf(),
    val donations: MutableList<Donation> = mutableListOf()
)