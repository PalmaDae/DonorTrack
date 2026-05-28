package com.example.domain.model.repository

import com.example.domain.model.scanner.ProductAnalysisResult

interface ScannerRepository {
    suspend fun scanBarcode(barcode: String): ProductAnalysisResult
}