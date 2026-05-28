package com.example.data.repository

import com.example.data.remote.api.DonorAPI
import com.example.data.remote.model.DonorPointModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DonorRepositoryImpl(
    private val api: DonorAPI
) : DonorRepository {

    override suspend fun getStationsForUserCity(citySlug: String): List<DonorPointModel> =
        withContext(Dispatchers.IO) {
            return@withContext try {

                val response = api.getAllPoints(citySlug = citySlug)


                response.results.filter { station ->

                    val isOpened = station.closed == null || !station.closed


                    val hasCoordinates = station.lat != null && station.lng != null


                    isOpened && hasCoordinates
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
}