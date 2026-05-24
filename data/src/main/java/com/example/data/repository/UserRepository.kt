package com.example.data.repository

import android.util.Log
import com.example.data.ServiceLocator
import com.example.data.mapper.UserModelMapper
import com.example.data.remote.RetrofitHelper
import com.example.data.remote.api.AuthAPI
import com.example.data.remote.model.ConfirmRegistrationRequest
import com.example.data.remote.model.UserRegistrationRequest
import com.example.domain.model.UserModel

class UserRepository(
    private val api: AuthAPI = RetrofitHelper.authAPI
) {
    suspend fun registerUser(request: UserRegistrationRequest): Result<String> {
        return try {
            Log.d("REG", "Calling api.registerUser with $request")
            val response = api.registerUser(request)
            Log.d("REG", "Response code: ${response.code()}")
            if (response.isSuccessful) {
                Result.success(response.body()?.message ?: "Успешно")
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = parseError(errorBody, response.code())
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun confirmRegistration(request: ConfirmRegistrationRequest): Result<String> {
        return try {
            Log.d("REG_CONFIRM", "Calling api.confirmRegistration with $request")
            val response = api.confirmRegistration(request)
            Log.d("REG_CONFIRM", "Response code: ${response.code()}")
            if (response.isSuccessful) {
                Result.success(response.body()?.message ?: "Успешно")
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = parseError(errorBody, response.code())
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun parseError(json: String?, code: Int): String {
        return "some error"
    }
}