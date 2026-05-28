package com.example.feature_map.state

import com.example.data.remote.model.PhoneNumber
import com.example.data.remote.model.ScheduleItem
import com.yandex.mapkit.geometry.Point
import com.example.data.remote.model.DonorPointModel

data class BloodStationUiModel(
    val id: Int,
    val title: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val phoneNumbers: List<PhoneNumber>? = emptyList(),
    val schedule: List<ScheduleItem>? = emptyList(),
    val bloodStatusMap: Map<String, String> = emptyMap()
)

data class MapUiState(
    val isLoading: Boolean = false,
    val stations: List<DonorPointModel> = emptyList(),
    val cameraTarget: Point? = null,
    val selectedStation: DonorPointModel? = null
)