package com.example.donortrack.data.model

import androidx.annotation.DrawableRes
import com.example.donortrack.R

data class User(
    @DrawableRes val avatar: Int = R.drawable.default_avatar,
    val avatarUri: String? = null,
    val name: String = "Your Name",
    val bloodType: BloodType = BloodType.APlus
)
