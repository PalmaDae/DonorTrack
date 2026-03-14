package com.example.donortrack.data.mapper

import com.example.donortrack.data.entity.UserEntity
import com.example.donortrack.data.model.UserModel

class UserModelMapper {
    fun map(
        input: UserEntity
    ): UserModel {
        return UserModel(
            name = input.name,
            bloodType = input.bloodType,
            hashPass = input.hashPass,
            login = input.login,
            email = input.email
        )
    }

    fun map(
        input: UserModel
    ): UserEntity {
        return UserEntity(
            name = input.name,
            bloodType = input.bloodType,
            hashPass = input.hashPass,
            login = input.login,
            email = input.email,
            avatarUri = input.avatarUri
        )
    }
}