package com.example.data

import android.content.Context
import androidx.room.Room
import com.example.data.local.database.InceptionDatabase
import com.example.data.mapper.UserModelMapper
import com.example.data.remote.RetrofitHelper
import com.example.data.remote.api.AuthAPI
import com.example.data.repository.UserRepository

object ServiceLocator {
    private var userRepository: UserRepository? = null

    fun getUserRepository(): UserRepository {
        return userRepository ?: UserRepository(
            authApi = RetrofitHelper.authAPI,
            userApi = RetrofitHelper.userAPI,
            mapper = UserModelMapper()
        ).also { userRepository = it }
    }
}