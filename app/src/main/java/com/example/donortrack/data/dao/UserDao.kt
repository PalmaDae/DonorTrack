package com.example.donortrack.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.donortrack.data.entity.UserEntity


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putUserData(user: UserEntity)

    @Update
    fun updateUserData(user: UserEntity)

    @Query("select * from users where login = :login limit 1")
    suspend fun getUserByLogin(login: String?): UserEntity?

//    @Transaction
//    @Query("select * from users")
//    fun getUserDonations(): List<UserDonations>
}