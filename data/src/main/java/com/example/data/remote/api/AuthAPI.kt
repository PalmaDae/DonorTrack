package com.example.data.remote.api

import com.example.data.remote.model.ConfirmRegistrationRequest
import com.example.data.remote.model.LoginRequest
import com.example.data.remote.model.UserRegistrationRequest
import com.example.data.remote.model.UserRegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {

    @POST("auth/registration")
    suspend fun registerUser(
        @Body request: UserRegistrationRequest
    ): Response<UserRegistrationResponse>

    @POST("auth/registration/confirm")
    suspend fun confirmRegistration(
        @Body request: ConfirmRegistrationRequest
    ): Response<UserRegistrationResponse>

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<Unit>
}