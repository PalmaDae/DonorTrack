package com.example.data.remote.api

import com.example.data.remote.model.ConfirmRegistrationRequest
import com.example.data.remote.model.UserRegistrationRequest
import com.example.data.remote.model.UserRegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthAPI {

    @POST("api/v1/auth/registration")
    suspend fun registerUser(
        @Body request: UserRegistrationRequest
    ): Response<UserRegistrationResponse>

    @POST("api/v1/auth/registration/confirm")
    suspend fun confirmRegistration(
        @Body request: ConfirmRegistrationRequest
    ): Response<UserRegistrationResponse>

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<Unit>
}