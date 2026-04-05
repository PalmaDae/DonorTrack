package com.example.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DonorAPI {
    @GET("blood_stations")
    suspend fun getAllPoints(
        @Query("city_slug") citySlug: String? = null
    ): DonorResponse

    @GET("blood_stations/{id}")
    suspend fun getDetail(@Path("id") pointId: Int): DonorPointDetailDto
}