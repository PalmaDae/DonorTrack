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
        if (userRepository == null) {
            val authApi: AuthAPI = RetrofitHelper.authAPI
            userRepository = UserRepository(authApi)
        }
        return userRepository ?: throw IllegalStateException("UserRepository is not initialized")
    }
}