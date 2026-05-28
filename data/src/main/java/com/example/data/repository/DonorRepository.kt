package com.example.data.repository

import com.example.data.remote.model.DonorPointModel

interface DonorRepository {
    suspend fun getStationsForUserCity(citySlug: String): List<DonorPointModel>
}