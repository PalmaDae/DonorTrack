package com.example.donortrack.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.donortrack.R

data class Donation(
    @StringRes val dateOfDonation: Int,
    @DrawableRes val typeOfDonation: Int,
    @DrawableRes val stageOfDonation: Int
)