package com.example.donortrack.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.donortrack.R

data class Bage(
    @DrawableRes val bageIcon: Int,
    @StringRes val bageDescription: Int
)

val bages = listOf(
    Bage(R.drawable.blood, R.string.litr),
    Bage(R.drawable.blood, R.string.redular),
    Bage(R.drawable.blood, R.string.experience)
)