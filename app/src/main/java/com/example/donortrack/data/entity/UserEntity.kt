package com.example.donortrack.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [Index(value = ["email"], unique = true)]
)
data class UserEntity(
    @PrimaryKey
    val login: String,
    @ColumnInfo(name = "name")
    val username: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "hash_pass")
    val hashPass: String,
    @ColumnInfo(name = "avatar_uri")
    val avatarUri: String?
)