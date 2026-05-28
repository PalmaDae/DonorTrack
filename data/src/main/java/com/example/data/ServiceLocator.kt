package com.example.data

import com.example.data.mapper.UserModelMapper
import com.example.data.remote.RetrofitHelper
import com.example.data.repository.ScannerRepositoryImpl
import com.example.data.repository.UserRepository
import com.example.domain.model.repository.ScannerRepository

object ServiceLocator {
    private var userRepository: UserRepository? = null
    private var scannerRepository: ScannerRepository? = null

    fun getUserRepository(): UserRepository {
        return userRepository ?: UserRepository(
            authApi = RetrofitHelper.authAPI,
            userApi = RetrofitHelper.userAPI,
            mapper = UserModelMapper()
        ).also { userRepository = it }
    }

    fun getScannerRepository(): ScannerRepository {
        return scannerRepository ?: ScannerRepositoryImpl(

            scannerApi = RetrofitHelper.scannerAPI
        ).also { scannerRepository = it }
    }
}