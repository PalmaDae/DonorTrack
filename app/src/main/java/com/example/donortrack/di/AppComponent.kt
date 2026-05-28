package com.example.donortrack.di

import com.example.donortrack.DonorTrackApplication
import com.example.feature_map.di.MapFeatureDependencies
import com.example.feature_main.di.MainFeatureDependencies
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface AppComponent : MapFeatureDependencies, MainFeatureDependencies {
    fun inject(application: DonorTrackApplication)
}