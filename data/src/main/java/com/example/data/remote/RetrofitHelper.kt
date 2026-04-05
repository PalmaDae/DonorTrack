package com.example.data.remote

import com.example.data.remote.api.DonorAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue

object RetrofitHelper {
    private const val BASE_URL = "https://api2.donorsearch.org/api/"

    val api: DonorAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DonorAPI::class.java)
    }
}