package com.example.data.local.dao

import com.example.data.local.entity.UserEntity

interface UserDao {
    suspend fun putUserData(user: UserEntity)

    fun updateUserData(user: UserEntity)

    suspend fun getUserByLogin(login: String?): UserEntity?

//    @Transaction
//    @Query("select * from users")
//    fun getUserDonations(): List<UserDonations>
}