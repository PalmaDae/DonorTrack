package com.example.feature_map.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.feature_map.component.StationBottomSheet
import com.example.feature_map.component.YandexMapView
import com.example.feature_map.di.DaggerMapComponent
import com.example.feature_map.di.MapFeatureDependenciesProvider
import com.example.feature_map.viewmodel.MapViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapApp(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = mapViewModelWithDagger()
) {
    val uiState by viewModel.uiState.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()



    val isSystemInDarkTheme = MaterialTheme.colorScheme.background.red < 0.5f

    LaunchedEffect(Unit) {
        viewModel.loadRealStations()
    }


    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            YandexMapView(
                stations = uiState.stations,
                cameraTarget = uiState.cameraTarget,
                isDarkTheme = isSystemInDarkTheme,
                onStationClick = { station ->
                    viewModel.selectStation(station)
                },
                modifier = Modifier.fillMaxSize()
            )


            if (uiState.isLoading) {

                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(76.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.9f),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }


            uiState.selectedStation?.let { station ->
                StationBottomSheet(
                    station = station,
                    sheetState = sheetState,
                    onDismissRequest = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            viewModel.selectStation(null)
                        }
                    }
                )
            }
        }
    }
}


@Composable
fun mapViewModelWithDagger(): MapViewModel {
    val context = LocalContext.current
    return viewModel(
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val provider = context.applicationContext as MapFeatureDependenciesProvider
                val appDependencies = provider.mapDependencies

                val mapComponent = DaggerMapComponent.factory().create(appDependencies)
                return mapComponent.getMapViewModel() as T
            }
        }
    )
}