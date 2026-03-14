package com.example.donortrack.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserDonations(
    @Embedded
    val user: UserEntity,
    @Relation(
        parentColumn = "login",
        entityColumn = "userCreatorLogin"
    )
    val donations: List<DonationEntity>
)
