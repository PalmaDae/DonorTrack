package com.example.feature_map.di

import com.example.data.repository.DonorRepository
import com.example.data.repository.UserRepository
import com.example.feature_map.viewmodel.MapViewModel
import dagger.Component


interface MapFeatureDependencies {
    fun donorRepository(): DonorRepository
    fun userRepository(): UserRepository
}


@Component(dependencies = [MapFeatureDependencies::class])
interface MapComponent {

    fun getMapViewModel(): MapViewModel

    @Component.Factory
    interface Factory {
        fun create(dependencies: MapFeatureDependencies): MapComponent
    }
}


interface MapFeatureDependenciesProvider {
    val mapDependencies: MapFeatureDependencies
}