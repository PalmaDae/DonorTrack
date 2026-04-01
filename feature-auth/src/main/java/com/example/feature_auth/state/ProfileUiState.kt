package com.example.feature_auth.state

import androidx.compose.runtime.Immutable
import com.example.domain.model.Bage
import com.example.domain.model.Donation
import com.example.domain.model.UserModel

@Immutable
data class ProfileUiState(
    val userModel: UserModel = UserModel(),
    val bages: List<Bage> = emptyList(),
    val donations: List<Donation> = emptyList()
)