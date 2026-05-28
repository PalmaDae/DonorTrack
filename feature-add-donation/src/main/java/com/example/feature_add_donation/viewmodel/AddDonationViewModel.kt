package com.example.feature_add_donation.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.DonationRepository // Импортируем репозиторий вместо API
import com.example.feature_add_donation.state.AddDonationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class AddDonationViewModel(
    private val donationRepository: DonationRepository = DonationRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddDonationUiState())
    val uiState = _uiState.asStateFlow()

    fun createDonation(context: Context, dateMillis: Long?, type: String, imageUri: Uri?) {
        if (dateMillis == null) {
            _uiState.update { it.copy(error = "Выберите дату донации") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, isSuccess = false) }
            try {
                val localDate = Instant.ofEpochMilli(dateMillis)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                val dateStr = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE)

                val certificateBytes = imageUri?.let { uri ->
                    context.contentResolver.openInputStream(uri)?.use { it.readBytes() }
                }

                donationRepository.createDonation(dateStr, type, certificateBytes)
                    .onSuccess {
                        _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                    }
                    .onFailure { error ->
                        _uiState.update { it.copy(isLoading = false, error = error.localizedMessage) }
                    }

            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.localizedMessage) }
            }
        }
    }
}