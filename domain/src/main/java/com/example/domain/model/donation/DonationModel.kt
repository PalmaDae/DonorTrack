package com.example.domain.model.donation

import android.net.Uri
import java.time.LocalDate

data class DonationModel(
    val id: Long? = null,
    val date: LocalDate,
    val donationType: DonationType,
    val certificateUri: Uri? = null,
    val donationStatus: DonationStatus = DonationStatus.CONFIRMED
)