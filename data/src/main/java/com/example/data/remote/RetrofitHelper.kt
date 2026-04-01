package com.example.data.remote

object RetrofitHelper {
    val baseUrl = "https://api2.donorsearch.org/api/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}