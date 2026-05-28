package com.example.data.remote.model

import com.google.gson.annotations.SerializedName


data class DonorResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: List<DonorPointModel>
)


data class DonorPointModel(
    val id: Int,
    val title: String,
    val address: String,
    val lat: Double?,
    val lng: Double?,
    val city: City?,
    val closed: Boolean?,

    @SerializedName("o_plus") val oPlus: String?,
    @SerializedName("o_minus") val oMinus: String?,
    @SerializedName("a_plus") val aPlus: String?,
    @SerializedName("a_minus") val aMinus: String?,
    @SerializedName("b_plus") val bPlus: String?,
    @SerializedName("b_minus") val bMinus: String?,
    @SerializedName("ab_plus") val abPlus: String?,
    @SerializedName("ab_minus") val abMinus: String?,

    val schedule: List<ScheduleItem>? = emptyList(),
    @SerializedName("phone_numbers") val phoneNumbers: List<PhoneNumber>? = emptyList(),
    val site: String?,
    val email: String?
) {
    val bloodStatusMap: Map<String, String>
        get() = mapOf(
            "O+" to (oPlus ?: "no_need"),
            "O-" to (oMinus ?: "no_need"),
            "A+" to (aPlus ?: "no_need"),
            "A-" to (aMinus ?: "no_need"),
            "B+" to (bPlus ?: "no_need"),
            "B-" to (bMinus ?: "no_need"),
            "AB+" to (abPlus ?: "no_need"),
            "AB-" to (abMinus ?: "no_need")
        )
}

data class City(
    val id: Int,
    val title: String,
    val slug: String
)

data class ScheduleItem(
    val id: Int?,
    val dow: String,
    val start: String,
    val end: String
)

data class PhoneNumber(
    val id: Int?,
    val phone: String,
    val comment: String?
)