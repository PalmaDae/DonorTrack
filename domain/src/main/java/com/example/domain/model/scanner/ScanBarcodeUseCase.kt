package com.example.domain.usecase.scanner


import com.example.domain.model.repository.ScannerRepository
import com.example.domain.model.scanner.ProductAnalysisResult

class ScanBarcodeUseCase(
    private val repository: ScannerRepository
) {
    suspend operator fun invoke(barcode: String): ProductAnalysisResult {
        return repository.scanBarcode(barcode)
    }
}