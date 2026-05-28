package com.example.data.utils

import com.example.domain.model.donation.DonationType
import java.time.LocalDate


data class UserDonationRecord(
    val id: Int,
    val date: LocalDate,
    val type: DonationType
)

object DonationCalculator {



    private val matrix: Map<DonationType, Map<DonationType, Long>> = mapOf(

        DonationType.BLOOD to mapOf(
            DonationType.BLOOD to 60L,
            DonationType.ERYTHROCYTE to 60L,
            DonationType.PLASMA to 30L,
            DonationType.THROMBOCYTE to 30L
        ),

        DonationType.ERYTHROCYTE to mapOf(
            DonationType.BLOOD to 60L,
            DonationType.ERYTHROCYTE to 60L,
            DonationType.PLASMA to 30L,
            DonationType.THROMBOCYTE to 30L
        ),

        DonationType.PLASMA to mapOf(
            DonationType.BLOOD to 14L,
            DonationType.ERYTHROCYTE to 14L,
            DonationType.PLASMA to 14L,
            DonationType.THROMBOCYTE to 14L
        ),

        DonationType.THROMBOCYTE to mapOf(
            DonationType.BLOOD to 14L,
            DonationType.ERYTHROCYTE to 14L,
            DonationType.PLASMA to 14L,
            DonationType.THROMBOCYTE to 14L
        )
    )

    fun calculateNextAvailableDates(history: List<UserDonationRecord>): Map<DonationType, LocalDate> {
        val today = LocalDate.now()


        val lastDonation = history.maxByOrNull { it.date }
            ?: return DonationType.entries.associateWith { today }

        val lastType = lastDonation.type
        val lastDate = lastDonation.date

        val rulesForLastType = matrix[lastType] ?: emptyMap()

        return DonationType.entries.associateWith { targetType ->
            val daysToWait = rulesForLastType[targetType] ?: 0L
            val availableDate = lastDate.plusDays(daysToWait)


            if (availableDate.isBefore(today)) today else availableDate
        }
    }
}