package com.example.data.mapper

import com.example.data.local.entity.UserEntity
import com.example.domain.model.BloodType
import com.example.domain.model.UserModel

class UserModelMapper {

    fun map(input: UserEntity): UserModel {
        return UserModel(
            name = input.name,
            bloodType = BloodType.fromString(input.bloodType),
            hashPass = input.hashPass,
            login = input.login,
            email = input.email,
            avatarUri = input.avatarUri
        )
    }

    fun map(input: UserModel): UserEntity {
        return UserEntity(
            name = input.name,
            bloodType = BloodType.toString(input.bloodType),
            hashPass = input.hashPass,
            login = input.login,
            email = input.email,
            avatarUri = input.avatarUri
        )
    }
}