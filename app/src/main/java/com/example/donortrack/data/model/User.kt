package com.example.donortrack.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.donortrack.R

data class User(
    @DrawableRes val avatar: Int = R.drawable.default_avatar,
    val avatarUri: String? = null,
    val name: String = "Your Name",
    val bloodType: BloodType = BloodType.APlus
)

val testUser = User(R.drawable.fcfc8ae10263bb1dd2a6ab51fd1fe08b5d07a7a6_full, name = "PalmaDae", bloodType = BloodType.APlus)