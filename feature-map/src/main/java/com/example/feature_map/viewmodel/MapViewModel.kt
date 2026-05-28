package com.example.feature_map.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yandex.mapkit.geometry.Point
import com.example.feature_map.state.MapUiState
import com.example.data.remote.model.DonorPointModel
import com.example.data.repository.DonorRepository
import com.example.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val repository: DonorRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MapUiState())
    val uiState = _uiState.asStateFlow()

    private val TAG = "DONOR_MAP_LOG"



    fun loadRealStations() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            try {
                Log.d(TAG, "Загрузка карты: запрашиваем профиль...")
                val userProfileResult = userRepository.getProfile()
                val rawCity = userProfileResult.getOrNull()?.city

                Log.d(TAG, "Город из профиля для карты: '$rawCity'")

                if (rawCity.isNullOrBlank()) {
                    Log.w(TAG, "Город не задан. Карта пустая, центрируем на Казань.")
                    _uiState.update {
                        it.copy(isLoading = false, stations = emptyList(), cameraTarget = Point(55.7887, 49.1221))
                    }
                    return@launch
                }


                val cityConfig = getCityConfig(rawCity)

                if (cityConfig == null) {
                    Log.w(TAG, "Город '$rawCity' не поддерживается. Очищаем карту.")
                    _uiState.update {
                        it.copy(isLoading = false, stations = emptyList(), cameraTarget = Point(55.7887, 49.1221))
                    }
                    return@launch
                }

                Log.d(TAG, "Запрашиваем станции для карты по слагу: ${cityConfig.slug}")
                val realStations = repository.getStationsForUserCity(cityConfig.slug)


                val firstValidStation = realStations.firstOrNull { it.lat != null && it.lng != null }
                val center = firstValidStation?.let { Point(it.lat!!, it.lng!!) } ?: cityConfig.defaultCenter

                Log.d(TAG, "Станций загружено: ${realStations.size}. Центр карты: ${center.latitude}, ${center.longitude}")

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        stations = realStations,
                        cameraTarget = center
                    )
                }
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при загрузке данных для карты", e)
                _uiState.update { it.copy(isLoading = false, stations = emptyList()) }
            }
        }
    }

    fun selectStation(station: DonorPointModel?) {
        _uiState.update { it.copy(selectedStation = station) }
    }


    private fun getCityConfig(city: String): CityConfig? {
        return when (city.trim().lowercase()) {
            "казань", "kazan" -> CityConfig("kazan", Point(55.7887, 49.1221))
            "москва", "moscow" -> CityConfig("moscow", Point(55.7558, 37.6173))
            "санкт-петербург", "питер", "спб", "spb", "saint-petersburg" -> CityConfig("spb", Point(59.9343, 30.3351))
            else -> null
        }
    }

    private data class CityConfig(val slug: String, val defaultCenter: Point)
}