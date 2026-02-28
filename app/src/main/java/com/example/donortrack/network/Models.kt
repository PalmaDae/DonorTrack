package com.example.donortrack.network

import com.google.gson.annotations.SerializedName

data class City(
    val title: String,
    val id: Int
)

data class CitiesResponse (
    @SerializedName("results")
    val cities: List<CityWrapper>
)

data class CityWrapper(
    val city: City
)