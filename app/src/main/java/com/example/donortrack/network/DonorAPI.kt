package com.example.donortrack.network

import retrofit2.http.GET
import retrofit2.http.Query

interface DonorAPI {
    @GET("api/cities/")
    suspend fun getCitiesInfo(
        @Query("all_bs") allBs: Boolean = true,
        @Query("country") country: Int = 1
    ) : CitiesResponse
}