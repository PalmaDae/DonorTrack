package com.example.domain.model.scanner

enum class MatchStatus {
    PERMITTED,
    WARNING,
    FORBIDDEN
}

data class DangerIngredient(
    val name: String,
    val reason: String
)

data class ProductAnalysisResult(
    val barcode: String,
    val productName: String,
    val fatContent: Double,
    val status: MatchStatus,
    val dangers: List<DangerIngredient>
)