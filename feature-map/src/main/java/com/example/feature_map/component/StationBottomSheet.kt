package com.example.feature_map.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.remote.model.DonorPointModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun StationBottomSheet(
    station: DonorPointModel,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = Color.White,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = station.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = station.address,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Светофор крови:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))


            ContextualFlowRow(
                itemCount = station.bloodStatusMap.size,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) { index ->
                val entry = station.bloodStatusMap.entries.toList()[index]
                val bloodType = entry.key
                val status = entry.value


                val (backgroundColor, textColor) = when (status) {
                    "critical" -> Color(0xFFE53935) to Color.White
                    "need" -> Color(0xFFFB8C00) to Color.White
                    "good" -> Color(0xFF4CAF50) to Color.White
                    else -> Color(0xFFF5F5F5) to Color.Gray
                }

                Box(
                    modifier = Modifier
                        .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = bloodType,
                        color = textColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            val phones = station.phoneNumbers
            val schedules = station.schedule

            if (!phones.isNullOrEmpty() || !schedules.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(color = Color(0xFFEEEEEE))
                Spacer(modifier = Modifier.height(16.dp))

                if (!phones.isNullOrEmpty()) {
                    Text(text = "Контакты:", fontWeight = FontWeight.Bold)

                    phones.forEach { phone ->
                        Text(text = phone.phone, style = MaterialTheme.typography.bodyMedium)
                        phone.comment?.let {
                            Text(text = it, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}