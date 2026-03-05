package com.example.donortrack.data.model

import androidx.annotation.StringRes
import com.example.donortrack.R

enum class BloodType(
    @StringRes val titleRes: Int
) {
        OPlus(R.string.blood_group_o_positive),
        OMinus(R.string.blood_group_o_negative),
        APlus(R.string.blood_group_a_positive),
        AMinus(R.string.blood_group_a_negative),
        BPlus(R.string.blood_group_b_positive),
        BMinus(R.string.blood_group_b_negative),
        ABPlus(R.string.blood_group_ab_positive),
        ABMinus(R.string.blood_group_ab_negative)
    }