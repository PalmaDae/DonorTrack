package com.example.feature_auth.utils

import com.example.domain.model.BloodType
import com.example.feature_auth.R

fun BloodType.toTitleRes(): Int = when (this) {
    BloodType.O_PLUS -> R.string.blood_group_o_positive
    BloodType.O_MINUS -> R.string.blood_group_o_negative
    BloodType.A_PLUS -> R.string.blood_group_a_positive
    BloodType.A_MINUS -> R.string.blood_group_a_negative
    BloodType.B_PLUS -> R.string.blood_group_b_positive
    BloodType.B_MINUS -> R.string.blood_group_b_negative
    BloodType.AB_PLUS -> R.string.blood_group_ab_positive
    BloodType.AB_MINUS -> R.string.blood_group_ab_negative
}