package com.example.donortrack.network

data class City(
    val title: String,
    val id: Int
)

data class CitiesResponse (
    val cities: List<City>
)