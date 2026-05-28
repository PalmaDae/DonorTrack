package com.example.feature_main.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.remote.model.DonorPointModel
import com.example.feature_common.R
import com.example.feature_main.di.DaggerMainComponent
import com.example.feature_main.di.MainFeatureDependenciesProvider
import com.example.feature_main.viewmodel.MainUiState
import com.example.feature_main.viewmodel.MainViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = mainViewModelWithDagger()
) {
    val uiState by viewModel.uiState.collectAsState()



    LaunchedEffect(Unit) {
        viewModel.loadStations()
    }

    val facts = stringArrayResource(id = R.array.facts_quotes)
    val randomItem = remember { facts.random() }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            FactCard(fact = randomItem)
            Spacer(modifier = Modifier.height(24.dp))
        }

        when (val state = uiState) {
            is MainUiState.Loading -> {
                item {
                    Box(modifier = Modifier.fillParentMaxHeight(0.5f), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
            is MainUiState.Error -> {
                item {
                    Box(modifier = Modifier.fillParentMaxHeight(0.5f), contentAlignment = Alignment.Center) {
                        Text(text = state.message, color = Color.Red, textAlign = TextAlign.Center)
                    }
                }
            }
            is MainUiState.Success -> {
                item {
                    Text(
                        text = stringResource(com.example.feature_main.R.string.blood_stations),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        textAlign = TextAlign.Start
                    )
                }

                if (state.stations.isEmpty()) {
                    item {
                        Text(
                            text = stringResource(com.example.feature_main.R.string.station_not_found),
                            modifier = Modifier.padding(16.dp),
                            color = Color.Gray
                        )
                    }
                } else {
                    items(state.stations, key = { it.id }) { station ->
                        MainScreenStationItem(station = station)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainScreenStationItem(station: DonorPointModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = station.title, style = MaterialTheme.typography.titleMedium, color = Color.Black, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = station.address, style = MaterialTheme.typography.bodySmall, color = Color.Gray)

            Spacer(modifier = Modifier.height(12.dp))


            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                station.bloodStatusMap.forEach { (bloodType, status) ->
                    val (backgroundColor, textColor) = when (status) {
                        "critical" -> Color(0xFFE53935) to Color.White
                        "need" -> Color(0xFFFB8C00) to Color.White
                        "good" -> Color(0xFF4CAF50) to Color.White
                        else -> Color(0xFFE0E0E0) to Color.DarkGray
                    }

                    Box(
                        modifier = Modifier
                            .background(backgroundColor, shape = RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(text = bloodType, color = textColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun FactCard(
    modifier: Modifier = Modifier,
    fact: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .heightIn(min = 85.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = fact,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun mainViewModelWithDagger(): MainViewModel {
    val context = LocalContext.current
    return viewModel(
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val provider = context.applicationContext as MainFeatureDependenciesProvider
                val appDependencies = provider.mainDependencies

                val mainComponent = DaggerMainComponent.factory().create(appDependencies)
                return mainComponent.getMainViewModel() as T
            }
        }
    )
}