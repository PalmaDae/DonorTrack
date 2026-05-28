package com.example.donortrack.di

import com.example.data.remote.api.DonorAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api2.donorsearch.org/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDonorApi(retrofit: Retrofit): DonorAPI {
        return retrofit.create(DonorAPI::class.java)
    }
}