package com.example.feature_scanner.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.scanner.MatchStatus
import com.example.domain.model.scanner.ProductAnalysisResult

@Composable
fun ProductAnalysisSummary(
    result: ProductAnalysisResult,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (statusColor, statusText) = when (result.status) {
        MatchStatus.PERMITTED -> Color(0xFF4CAF50) to "ПОДХОДИТ ДЛЯ ДОНОРОВ"
        MatchStatus.WARNING -> Color(0xFFFB8C00) to "НЕ РЕКОМЕНДУЕТСЯ"
        MatchStatus.FORBIDDEN -> Color(0xFFE53935) to "КАТЕГОРИЧЕСКИ НЕЛЬЗЯ"
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(statusColor, shape = RoundedCornerShape(8.dp))
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = statusText,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = result.productName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Штрих-код: ${result.barcode}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))


            if (result.dangers.isEmpty()) {
                Text(
                    text = "Продукт полностью чист. Никаких опасных жиров или химии перед сдачей крови не обнаружено!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
            } else {
                Text(
                    text = "Что напрягает в продукте:",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))

                result.dangers.forEach { danger ->
                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .background(statusColor, shape = RoundedCornerShape(3.dp))
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = danger.name,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                        Text(
                            text = danger.reason,
                            fontSize = 13.sp,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(start = 14.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onCloseClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Сканировать другой продукт")
            }
        }
    }
}