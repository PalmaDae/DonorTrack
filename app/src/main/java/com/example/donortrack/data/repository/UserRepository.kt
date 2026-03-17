package com.example.donortrack.data.repository

import com.example.donortrack.data.dao.UserDao
import com.example.donortrack.data.mapper.UserModelMapper
import com.example.donortrack.data.model.UserModel
import com.example.donortrack.util.ServiceLocator


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