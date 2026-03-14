package com.example.donortrack.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.donortrack.data.dao.UserDao
import com.example.donortrack.data.entity.DonationEntity
import com.example.donortrack.data.entity.UserEntity


@Database(entities = [UserEntity::class, DonationEntity::class], version = 1)
abstract class InceptionDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
}