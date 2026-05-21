package com.example.feature_add_donation.viewmodel

import android.content.ContentResolver
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.DonationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.example.feature_add_donation.state.AddDonationUiState
import com.example.domain.model.donation.DonationType
import com.example.feature_add_donation.utils.toByteArray
import kotlinx.coroutines.launch
import java.time.LocalDate

class AddDonationViewModel(
    private val repository: DonationRepository = DonationRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        AddDonationUiState(date = LocalDate.now().toString())
    )
    val uiState: StateFlow<AddDonationUiState> = _uiState

    fun onDateChange(date: String) {
        _uiState.update { it.copy(date = date) }
    }

    fun onTypeChange(type: DonationType) {
        _uiState.update { it.copy(type = type) }
    }

    fun onCertificateChange(uri: String?) {
        _uiState.update { it.copy(certificateUri = uri) }
    }

    fun submitDonation(contentResolver: ContentResolver) {
        val state = _uiState.value

        viewModelScope.launch {

            val bytes = state.certificateUri?.let {
                Uri.parse(it).toByteArray(contentResolver)
            }

            repository.createDonation(
                date = state.date,
                type = state.type.name,
                certificateBytes = bytes
            )
        }
    }
}