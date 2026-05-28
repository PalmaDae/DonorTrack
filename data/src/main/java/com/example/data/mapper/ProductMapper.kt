package com.example.data.mapper

import com.example.data.remote.model.ProductResponse
import com.example.domain.model.scanner.DangerIngredient
import com.example.domain.model.scanner.MatchStatus
import com.example.domain.model.scanner.ProductAnalysisResult

fun ProductResponse.toDomain(): ProductAnalysisResult {
    return ProductAnalysisResult(
        barcode = this.barcode,
        productName = this.productName,
        fatContent = this.fatContent,
        status = try {
            MatchStatus.valueOf(this.status)
        } catch (e: Exception) {
            MatchStatus.WARNING
        },
        dangers = this.dangers.map { dto ->
            DangerIngredient(
                name = dto.name,
                reason = dto.reason
            )
        }
    )
}