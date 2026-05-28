package com.example.data.repository

import android.util.Log
import com.example.data.mapper.UserModelMapper
import com.example.data.remote.RetrofitHelper
import com.example.data.remote.api.AuthAPI
import com.example.data.remote.api.DonorAPI
import com.example.data.remote.api.UserAPI
import com.example.data.remote.model.BloodTypeChangeDto
import com.example.data.remote.model.CityChangeDto
import com.example.data.remote.model.ConfirmRegistrationRequest
import com.example.data.remote.model.EmailChangeDto
import com.example.data.remote.model.LoginRequest
import com.example.data.remote.model.PasswordChangeDto
import com.example.data.remote.model.UserRegistrationRequest
import com.example.domain.model.UserModel

class UserRepository(
    private val authApi: AuthAPI = RetrofitHelper.authAPI,
    private val userApi: UserAPI = RetrofitHelper.userAPI,
    private val donorApi: DonorAPI = RetrofitHelper.donorAPI,
    private val mapper: UserModelMapper = UserModelMapper()
) {
    suspend fun registerUser(request: UserRegistrationRequest): Result<String> {
        return try {
            val response = authApi.registerUser(request)
            if (response.isSuccessful) Result.success(response.body()?.message ?: "Успешно")
            else Result.failure(Exception("Error ${response.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun confirmRegistration(request: ConfirmRegistrationRequest): Result<String> {
        return try {
            val response = authApi.confirmRegistration(request)
            if (response.isSuccessful) Result.success(response.body()?.message ?: "Успешно")
            else Result.failure(Exception("Error ${response.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProfile(): Result<UserModel> {
        return try {
            val response = userApi.getUserProfile()

            if (response.isSuccessful) {
                val dto = response.body()
                if (dto != null) {
                    Result.success(mapper.map(dto))
                } else {
                    Result.failure(Exception("Сервер вернул пустой профиль"))
                }
            } else {
                Result.failure(Exception("Ошибка загрузки профиля: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun changeEmail(email: String): Result<Unit> = try {
        val response = userApi.changeEmail(EmailChangeDto(email))
        if (response.isSuccessful) Result.success(Unit)
        else Result.failure(Exception("Ошибка изменения email: ${response.code()}"))
    } catch (e: Exception) { Result.failure(e) }

    suspend fun changeCity(city: String): Result<Unit> = try {
        val response = userApi.changeCity(CityChangeDto(city))
        if (response.isSuccessful) Result.success(Unit)
        else Result.failure(Exception("Ошибка изменения города"))
    } catch (e: Exception) { Result.failure(e) }

    suspend fun changeBloodType(bloodType: String): Result<Unit> = try {
        val response = userApi.changeBloodType(BloodTypeChangeDto(bloodType))
        if (response.isSuccessful) Result.success(Unit)
        else Result.failure(Exception("Ошибка изменения группы крови"))
    } catch (e: Exception) { Result.failure(e) }

    suspend fun changePassword(dto: PasswordChangeDto): Result<Unit> = try {
        val response = userApi.changePassword(dto)
        if (response.isSuccessful) Result.success(Unit)
        else Result.failure(Exception("Ошибка: ${response.code()}"))
    } catch (e: Exception) { Result.failure(e) }

    suspend fun loginUser(login: String, password: String): Result<Unit> {
        return try {

            val response = authApi.login(LoginRequest(username = login, password = password))

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                val errorMessage = when(response.code()) {
                    401 -> "Неверный логин или пароль"
                    404 -> "Пользователь не найден"
                    else -> "Ошибка сервера: ${response.code()}"
                }
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Нет соединения с сервером. Проверьте интернет."))
        }
    }
}