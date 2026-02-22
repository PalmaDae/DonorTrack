package com.example.donortrack.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.donortrack.R

data class User(
    @DrawableRes val avatar: Int,
    @StringRes val name: Int,
    @StringRes val bloodType: Int
)

val testUser = User(R.drawable.fcfc8ae10263bb1dd2a6ab51fd1fe08b5d07a7a6_full, R.string.user_name, R.string.blood_group_b_positive)