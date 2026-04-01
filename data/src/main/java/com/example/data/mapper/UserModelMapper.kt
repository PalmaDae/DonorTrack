package com.example.data.mapper

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