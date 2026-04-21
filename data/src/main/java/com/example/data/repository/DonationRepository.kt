package com.example.data.repository

import com.example.data.remote.RetrofitHelper
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class DonationRepository {

    private val api = RetrofitHelper.donationAPI

    suspend fun createDonation(
        date: String,
        type: String,
        certificateBytes: ByteArray?
    ) {
        val dateBody = date.toRequestBody("text/plain".toMediaTypeOrNull())
        val typeBody = type.toRequestBody("text/plain".toMediaTypeOrNull())

        val filePart = certificateBytes?.let {
            MultipartBody.Part.createFormData(
                "certificate",
                "certificate.jpg",
                it.toRequestBody("image/*".toMediaTypeOrNull())
            )
        }

        api.createDonation(dateBody, typeBody, filePart)
    }
}