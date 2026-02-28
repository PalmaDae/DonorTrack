package com.example.donortrack.network.api

import com.example.donortrack.network.model.CityList
import retrofit2.http.GET
import retrofit2.http.Query

interface DonorAPI {
    @GET("cities/")
    suspend fun getCitiesInfo(
        @Query("all_bs") allBs: Boolean = true,
        @Query("country") country: Int = 1
    ) : CityList
}