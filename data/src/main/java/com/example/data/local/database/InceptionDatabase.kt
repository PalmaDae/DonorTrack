package com.example.data.local.database

import com.example.data.local.dao.UserDao

abstract class InceptionDatabase{
    abstract fun userDao(): UserDao
}