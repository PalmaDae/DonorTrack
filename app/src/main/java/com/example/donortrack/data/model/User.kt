package com.example.donortrack.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.donortrack.R

data class User(
    @DrawableRes val avatar: Int? = R.drawable.default_avatar,
    @StringRes val name: Int? = R.string.user_name,
    @StringRes val bloodType: Int? = R.string.blood_group_a_positive
)

val testUser = User(R.drawable.fcfc8ae10263bb1dd2a6ab51fd1fe08b5d07a7a6_full, R.string.user_name, R.string.blood_group_a_positive)