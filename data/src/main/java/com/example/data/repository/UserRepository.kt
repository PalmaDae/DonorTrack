package com.example.data.repository

import com.example.data.ServiceLocator
import com.example.data.mapper.UserModelMapper
import com.example.domain.model.UserModel

class UserRepository(
    private val mapper: UserModelMapper
) {
    private fun getUserDao() = ServiceLocator.getDatabase().userDao()

    suspend fun createNewUser(userModel: UserModel) {
        val isExistUser = getUserDao().getUserByLogin(userModel.login)

        if (isExistUser == null) {
            getUserDao().putUserData(mapper.map(userModel))
        } else {
            throw Exception("User is already exist")
        }
    }

    suspend fun getUserByLogin(login: String): UserModel? {
        val entity = getUserDao().getUserByLogin(login)

        return entity?.let { mapper.map(it) }
    }
}