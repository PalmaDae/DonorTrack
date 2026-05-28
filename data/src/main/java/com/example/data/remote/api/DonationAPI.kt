package com.example.data.remote.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface DonationAPI {
    @Multipart
    @POST("api/v1/profile/donations")
    suspend fun addDonation(
        @Part("date") date: RequestBody,
        @Part("donationType") donationType: RequestBody,
        @Part certificateFile: MultipartBody.Part?
    ): Response<Unit>
}