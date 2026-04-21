package com.example.data.remote.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface DonationAPI {

    @Multipart
    @POST("api/donation/create")
    suspend fun createDonation(
        @Part("date") date: RequestBody,
        @Part("type") type: RequestBody,
        @Part certificate: MultipartBody.Part?
    ): Response<Unit>
}