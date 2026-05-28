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
    ): Result<Unit> {
        return try {
            val dateBody = date.toRequestBody("text/plain".toMediaTypeOrNull())
            val typeBody = type.toRequestBody("text/plain".toMediaTypeOrNull())

            val filePart = certificateBytes?.let {
                MultipartBody.Part.createFormData(
                    "certificateFile",
                    "certificate.jpg",
                    it.toRequestBody("image/jpeg".toMediaTypeOrNull())
                )
            }


            val response = api.addDonation(dateBody, typeBody, filePart)

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Ошибка сервера: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}