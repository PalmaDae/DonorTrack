package com.example.feature_main.di

import com.example.data.repository.DonorRepository
import com.example.data.repository.UserRepository
import com.example.feature_main.viewmodel.MainViewModel
import dagger.Component


interface MainFeatureDependencies {
    fun donorRepository(): DonorRepository
    fun userRepository(): UserRepository
}


@Component(dependencies = [MainFeatureDependencies::class])
interface MainComponent {
    fun getMainViewModel(): MainViewModel

    @Component.Factory
    interface Factory {
        fun create(dependencies: MainFeatureDependencies): MainComponent
    }
}


interface MainFeatureDependenciesProvider {
    val mainDependencies: MainFeatureDependencies
}