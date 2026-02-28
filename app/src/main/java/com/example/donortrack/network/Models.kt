package com.example.donortrack.network

import com.google.gson.annotations.SerializedName

data class CityList(
    @SerializedName("next")
    val nextUrl: String,
    @SerializedName("previous")
    val prevUrl: String,
    val results: List<City>
)

data class City(
    val title: String,
    val id: Int
)
