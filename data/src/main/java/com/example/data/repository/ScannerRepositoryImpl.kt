package com.example.data.repository

import com.example.data.mapper.toDomain
import com.example.data.remote.api.ScannerApi
import com.example.domain.model.repository.ScannerRepository
import com.example.domain.model.scanner.ProductAnalysisResult

class ScannerRepositoryImpl(
    private val scannerApi: ScannerApi
) : ScannerRepository {

    override suspend fun scanBarcode(barcode: String): ProductAnalysisResult {
        return scannerApi.scanProduct(barcode).toDomain()
    }
}