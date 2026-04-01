package com.example.data.local.database

@Database(entities = [UserEntity::class], version = 1)
abstract class InceptionDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
}