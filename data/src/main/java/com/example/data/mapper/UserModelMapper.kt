package com.example.data.mapper

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.data.remote.model.NetworkDonationEntity
import com.example.data.remote.model.ProfileDTO
import com.example.domain.model.BloodType
import com.example.domain.model.UserModel
import com.example.domain.model.donation.DonationModel
import com.example.domain.model.donation.DonationType
import com.example.domain.model.donation.DonationStatus
import java.time.LocalDate

class UserModelMapper {

    @RequiresApi(Build.VERSION_CODES.O)
    fun map(dto: ProfileDTO): UserModel {
        return UserModel(

            name = dto.username ?: "Пользователь",
            login = dto.username ?: "Пользователь",
            email = dto.email ?: "",
            city = dto.city ?: "Город не указан",
            bloodType = BloodType.fromString(dto.bloodType),
            donations = dto.donations?.map { mapDonation(it) } ?: emptyList()
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun mapDonation(entity: NetworkDonationEntity): DonationModel {
        return DonationModel(
            id = entity.id,
            date = LocalDate.parse(entity.date.trim()),
            donationType = try {
                DonationType.valueOf(entity.donationType)
            } catch (e: IllegalArgumentException) {
                DonationType.BLOOD
            },
            donationStatus = try {
                DonationStatus.valueOf(entity.donationStatus)
            } catch (e: Exception) {
                DonationStatus.CONFIRMED
            },
            certificateUri = entity.certificate?.let { Uri.parse(it) }
        )
    }
}