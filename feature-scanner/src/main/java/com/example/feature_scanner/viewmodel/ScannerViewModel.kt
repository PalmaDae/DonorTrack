package com.example.feature_scanner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.scanner.ProductAnalysisResult
import com.example.domain.model.repository.ScannerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface ScannerUiState {
    object Idle : ScannerUiState
    object Loading : ScannerUiState
    data class Success(val result: ProductAnalysisResult) : ScannerUiState
    data class Error(val message: String) : ScannerUiState
}

class ScannerViewModel(
    private val scannerRepository: ScannerRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ScannerUiState>(ScannerUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun onBarcodeScanned(barcode: String) {

        if (_uiState.value is ScannerUiState.Loading) return

        viewModelScope.launch {
            _uiState.value = ScannerUiState.Loading

            try {

                val result = scannerRepository.scanBarcode(barcode)
                _uiState.value = ScannerUiState.Success(result)
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = ScannerUiState.Error(
                    message = e.localizedMessage ?: "Ошибка при сканировании продукта"
                )
            }
        }
    }

    fun resetScanner() {
        _uiState.value = ScannerUiState.Idle
    }
}