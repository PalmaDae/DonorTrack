package com.example.donortrack.util

import android.content.Context
import androidx.room.Room
import com.example.donortrack.data.InceptionDatabase
import com.example.donortrack.data.dao.UserDao
import com.example.donortrack.data.mapper.UserModelMapper
import com.example.donortrack.data.repository.UserRepository

object ServiceLocator {
    private const val DB_NAME = "some.db"
    private val userModelMapper = UserModelMapper()
    private var inceptionDatabase: InceptionDatabase? = null

    private val _userRepository by lazy {
        UserRepository(
            userModelMapper
        )
    }

    fun initDatabase(appCtx: Context) {
        if (inceptionDatabase == null) {
            inceptionDatabase = Room.databaseBuilder(
                appCtx,
                InceptionDatabase::class.java,
                DB_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    fun getDatabase(): InceptionDatabase = inceptionDatabase ?: throw IllegalStateException("DataBase is not initialized")
    fun getUserRepository(): UserRepository = _userRepository
}