package com.example.domain.model

import androidx.annotation.DrawableRes
import com.example.donortrack.R

data class UserModel(
    @DrawableRes val avatar: Int = R.drawable.default_avatar,
    val avatarUri: String? = null,
    val name: String = "Your Name",
    val bloodType: BloodType = BloodType.APlus,
    val hashPass: String = "",
    val login: String = "",
    val email: String = ""
)
