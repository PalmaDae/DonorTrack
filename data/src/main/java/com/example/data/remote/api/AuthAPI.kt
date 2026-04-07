package com.example.data.remote.api

import com.example.data.remote.model.UserRegistrationRequest
import com.example.data.remote.model.UserRegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthAPI {
    @POST("api/register")
    @Headers("Content-Type: application/json")
    fun registerUser(
        @Body request: UserRegistrationRequest
    ): Call<UserRegistrationResponse>
}