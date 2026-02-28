package com.example.donortrack.network

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DonorAPI {
    @GET("cities/")
    suspend fun getCitiesInfo(
        @Query("all_bs") allBs: Boolean = true,
        @Query("country") country: Int = 1
    ) : CitiesResponse
}