package com.example.donortrack

import android.app.Application
import com.example.donortrack.di.AppComponent
import com.example.donortrack.di.DaggerAppComponent
import com.example.feature_map.di.MapFeatureDependencies
import com.example.feature_map.di.MapFeatureDependenciesProvider
import com.example.feature_main.di.MainFeatureDependencies
import com.example.feature_main.di.MainFeatureDependenciesProvider
import com.yandex.mapkit.MapKitFactory

class DonorTrackApplication : Application(), MapFeatureDependenciesProvider, MainFeatureDependenciesProvider {

    lateinit var appComponent: AppComponent
        private set

    override val mapDependencies: MapFeatureDependencies
        get() = appComponent

    override val mainDependencies: MainFeatureDependencies
        get() = appComponent

    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey("9d71424e-97a6-4ad2-b1ab-caf4e16298a7")
        MapKitFactory.initialize(this)

        appComponent = DaggerAppComponent.builder().build()
    }
}