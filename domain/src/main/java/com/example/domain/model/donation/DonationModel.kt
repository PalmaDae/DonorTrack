package com.example.domain.model.donation

import android.net.Uri
import java.time.LocalDate

data class DonationModel(
    val date: LocalDate,
    val donationType: DonationType,
    val certicateUri: Uri,
    val donationStatus: DonationStatus
)