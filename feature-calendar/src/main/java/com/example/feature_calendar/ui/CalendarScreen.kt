package com.example.feature_calendar.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.model.donation.DonationType
import com.example.feature_calendar.viewmodel.CalendarViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Composable
fun CalendarApp(
    viewModel: CalendarViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val dateFormatter = remember { DateTimeFormatter.ofPattern("dd MMMM yyyy") }


    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Календарь донаций",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            uiState.lastDonation?.let { last ->
                Text(
                    text = "Последняя донация: ${last.date.format(dateFormatter)} (${translateType(last.type)})",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            uiState.nextAvailableDates.forEach { (type, availableDate) ->
                val today = LocalDate.now()
                val daysLeft = ChronoUnit.DAYS.between(today, availableDate)
                val isAvailable = daysLeft <= 0




                val cardContainerColor = if (isAvailable) {

                    if (MaterialTheme.colorScheme.isLight()) Color(0xFFE8F5E9) else Color(0xFF1B3A24)
                } else {
                    MaterialTheme.colorScheme.surfaceContainer
                }

                val contentTextColor = if (isAvailable) {
                    if (MaterialTheme.colorScheme.isLight()) Color(0xFF2E7D32) else Color(0xFF81C784)
                } else {
                    MaterialTheme.colorScheme.onSurface
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = cardContainerColor
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = translateType(type),
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = if (isAvailable) "Можно сдавать" else "Доступно с: ${availableDate.format(dateFormatter)}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = contentTextColor
                            )
                        }

                        if (!isAvailable) {

                            Text(
                                text = "еще $daysLeft дн.",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ColorScheme.isLight(): Boolean {
    return this.background.red > 0.5f
}

private fun translateType(type: DonationType): String = when (type) {
    DonationType.BLOOD -> "Кроводача"
    DonationType.PLASMA -> "Плазмодача"
    DonationType.ERYTHROCYTE -> "Эритроцитаферез"
    DonationType.THROMBOCYTE -> "Тромбоцитаферез"
}