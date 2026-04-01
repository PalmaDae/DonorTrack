package com.example.data

import android.content.Context
import androidx.room.Room
import com.example.data.local.database.InceptionDatabase
import com.example.data.mapper.UserModelMapper
import com.example.data.repository.UserRepository

object ServiceLocator {
    private const val DB_NAME = "some.db"

    private val userModelMapper = UserModelMapper()
    private var inceptionDatabase: InceptionDatabase? = null
    private var userRepository: UserRepository? = null

    fun initDatabase(appCtx: Context) {
        if (inceptionDatabase == null) {
//            inceptionDatabase = Room.databaseBuilder(
//                appCtx,
//                InceptionDatabase::class.java,
//                DB_NAME
//            )
//                .fallbackToDestructiveMigration()
//                .build()
        }
        if (userRepository == null) {
            userRepository = UserRepository(userModelMapper)
        }
    }

    fun getDatabase(): InceptionDatabase =
        inceptionDatabase ?: throw IllegalStateException("Database is not initialized")

    fun getUserRepository(): UserRepository =
        userRepository ?: throw IllegalStateException("UserRepository is not initialized")
}