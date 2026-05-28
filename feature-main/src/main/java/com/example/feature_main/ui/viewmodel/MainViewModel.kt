package com.example.feature_main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.remote.model.DonorPointModel
import com.example.data.repository.DonorRepository
import com.example.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface MainUiState {
    object Loading : MainUiState
    data class Success(val stations: List<DonorPointModel>) : MainUiState
    data class Error(val message: String) : MainUiState
}

class MainViewModel @Inject constructor(
    private val repository: DonorRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState

    private val TAG = "DONOR_TRACK_LOG"

    init {
        loadStations()
    }

    fun loadStations() {
        viewModelScope.launch {
            _uiState.value = MainUiState.Loading
            try {
                Log.d(TAG, "Запуск loadStations(). Запрашиваем профиль...")
                val userProfileResult = userRepository.getProfile()


                Log.d(TAG, "Результат профиля: isSuccess=${userProfileResult.isSuccess}, value=${userProfileResult.getOrNull()}")

                val rawCity = userProfileResult.getOrNull()?.city
                Log.d(TAG, "Получен город из профиля (rawCity): '$rawCity'")


                if (rawCity.isNullOrBlank()) {
                    Log.w(TAG, "Город пользователя пустой или null. Запрос к API отменен. Выводим пустой экран.")
                    _uiState.value = MainUiState.Success(emptyList())
                    return@launch
                }


                val userCitySlug = getCitySlug(rawCity)
                Log.d(TAG, "Смаппленный slug для API: '$userCitySlug'")

                if (userCitySlug == null) {
                    Log.w(TAG, "Город '$rawCity' не поддерживается (нет перевода). Выводим пустой список карточек.")
                    _uiState.value = MainUiState.Success(emptyList())
                    return@launch
                }


                Log.d(TAG, "Отправка сетевого запроса для города: $userCitySlug")
                val result = repository.getStationsForUserCity(userCitySlug)
                Log.d(TAG, "Успешно загружено станций: ${result.size}")

                _uiState.value = MainUiState.Success(result)
            } catch (e: Exception) {
                Log.e(TAG, "Глобальная ошибка во время загрузки станций!", e)
                _uiState.value = MainUiState.Error(e.localizedMessage ?: "Не удалось загрузить данные")
            }
        }
    }


    private fun getCitySlug(city: String): String? {
        val cleanedCity = city.trim().lowercase()
        return when (cleanedCity) {
            "казань", "kazan" -> "kazan"
            "москва", "moscow" -> "moscow"
            "санкт-петербург", "питер", "спб", "spb", "saint-petersburg" -> "spb"
            else -> {
                Log.d(TAG, "getCitySlug: город '$city' не попал в список известных городов.")
                null
            }
        }
    }
}