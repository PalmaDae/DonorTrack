package com.example.donortrack.di

import com.example.data.ServiceLocator
import com.example.data.remote.api.DonorAPI
import com.example.data.repository.DonorRepository
import com.example.data.repository.DonorRepositoryImpl
import com.example.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideDonorRepository(api: DonorAPI): DonorRepository {

        return DonorRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return ServiceLocator.getUserRepository()
    }
}