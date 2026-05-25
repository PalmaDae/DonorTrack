package com.example.data.mapper

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.data.local.entity.UserEntity
import com.example.data.remote.model.NetworkDonationEntity
import com.example.data.remote.model.ProfileDTO
import com.example.domain.model.BloodType
import com.example.domain.model.UserModel
import com.example.domain.model.donation.DonationModel
import com.example.domain.model.donation.DonationType
import java.time.LocalDate

class UserModelMapper {
    @RequiresApi(Build.VERSION_CODES.O)
    fun map(dto: ProfileDTO): UserModel {
        return UserModel(
            name = dto.username,
            login = dto.username,
            email = dto.email,
            city = dto.city,
            bloodType = dto.bloodType?.let { BloodType.fromString(it) } ?: BloodType.A_PLUS,
            donations = dto.donations.map { mapDonation(it) }
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun mapDonation(entity: NetworkDonationEntity): DonationModel {
        return DonationModel(
            id = entity.id,
            date = LocalDate.parse(entity.date),
            donationType = DonationType.valueOf(entity.donationType),
            certificateUri = entity.certificate?.let { Uri.parse(it) }
        )
    }
}