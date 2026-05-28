package com.example.data.remote.api

import com.example.data.remote.model.BloodTypeChangeDto
import com.example.data.remote.model.CityChangeDto
import com.example.data.remote.model.EmailChangeDto
import com.example.data.remote.model.PasswordChangeDto
import com.example.data.remote.model.ProfileDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserAPI {


    @GET("profile")
    suspend fun getUserProfile(): Response<ProfileDTO>


    @POST("profile/edit/email")
    suspend fun changeEmail(@Body dto: EmailChangeDto): Response<Unit>


    @POST("profile/edit/password")
    suspend fun changePassword(@Body dto: PasswordChangeDto): Response<Unit>


    @POST("profile/edit/bloodtype")
    suspend fun changeBloodType(@Body dto: BloodTypeChangeDto): Response<Unit>


    @POST("profile/edit/city")
    suspend fun changeCity(@Body dto: CityChangeDto): Response<Unit>
}