package com.example.data.remote.api

import com.example.data.remote.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ScannerApi {

    @GET("products/scan/{barcode}")
    suspend fun scanProduct(
        @Path("barcode") barcode: String
    ): ProductResponse
}