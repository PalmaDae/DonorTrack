package com.example.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Donation(
    @StringRes val dateOfDonation: Int,
    @DrawableRes val typeOfDonation: Int,
    @DrawableRes val stageOfDonation: Int
)