package com.example.donortrack.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.donortrack.R

data class Donation(
    @StringRes val dateOfDonation: Int,
    @DrawableRes val typeOfDonation: Int,
    @DrawableRes val stageOfDonation: Int
)

val donations = listOf(
    Donation(R.string.date1, R.drawable.blood, R.drawable.plasma),
    Donation(R.string.date2, R.drawable.blood, R.drawable.erythrocyte),
    Donation(R.string.date3, R.drawable.plasma, R.drawable.thrombocyte),
    Donation(R.string.date4, R.drawable.blood, R.drawable.plasma),
    Donation(R.string.date5, R.drawable.thrombocyte, R.drawable.erythrocyte),
    Donation(R.string.date6, R.drawable.thrombocyte, R.drawable.plasma),
    Donation(R.string.date7, R.drawable.blood, R.drawable.thrombocyte),
    Donation(R.string.date8, R.drawable.thrombocyte, R.drawable.plasma),
    Donation(R.string.date2, R.drawable.blood, R.drawable.erythrocyte),
    Donation(R.string.date3, R.drawable.plasma, R.drawable.thrombocyte),
    Donation(R.string.date4, R.drawable.blood, R.drawable.plasma),
    Donation(R.string.date5, R.drawable.thrombocyte, R.drawable.erythrocyte),
    Donation(R.string.date6, R.drawable.thrombocyte, R.drawable.plasma),
    Donation(R.string.date7, R.drawable.blood, R.drawable.thrombocyte),
    Donation(R.string.date8, R.drawable.thrombocyte, R.drawable.plasma),
    Donation(R.string.date2, R.drawable.blood, R.drawable.erythrocyte),
    Donation(R.string.date3, R.drawable.plasma, R.drawable.thrombocyte),
    Donation(R.string.date4, R.drawable.blood, R.drawable.plasma),
    Donation(R.string.date5, R.drawable.thrombocyte, R.drawable.erythrocyte),
    Donation(R.string.date6, R.drawable.thrombocyte, R.drawable.plasma),
    Donation(R.string.date7, R.drawable.blood, R.drawable.thrombocyte),
    Donation(R.string.date8, R.drawable.thrombocyte, R.drawable.plasma),
    Donation(R.string.date9, R.drawable.blood, R.drawable.plasma),
    Donation(R.string.date10, R.drawable.erythrocyte, R.drawable.blood)
)