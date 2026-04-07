package com.example.data.remote

import com.example.data.remote.api.AuthAPI
import com.example.data.remote.api.DonorAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue

object RetrofitHelper {
    private const val DONOR_URL = "https://api2.donorsearch.org/api/"
    private const val AUTH_URL = "http://10.0.2.2:8080/"

    val donorAPI: DonorAPI by lazy {
        Retrofit.Builder()
            .baseUrl(DONOR_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DonorAPI::class.java)
    }

    val authAPI: AuthAPI by lazy {
        Retrofit.Builder()
            .baseUrl(AUTH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthAPI::class.java)
    }
}