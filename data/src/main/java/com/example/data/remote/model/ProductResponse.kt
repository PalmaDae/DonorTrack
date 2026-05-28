package com.example.data.remote.model

import com.google.gson.annotations.SerializedName

import com.example.domain.model.scanner.DangerIngredient
import com.example.domain.model.scanner.MatchStatus
import com.example.domain.model.scanner.ProductAnalysisResult

data class ProductResponse(
    @SerializedName("barcode") val barcode: String,
    @SerializedName("productName") val productName: String,
    @SerializedName("fatContent") val fatContent: Double,
    @SerializedName("status") val status: String,
    @SerializedName("dangers") val dangers: List<DangerDto>
)

data class DangerDto(
    @SerializedName("name") val name: String,
    @SerializedName("reason") val reason: String
)