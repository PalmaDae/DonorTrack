package com.example.donortrack.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.donortrack.R
import com.example.donortrack.ui.theme.DonorTrackTheme

class ProfileScreen {



    @Composable
    fun ProfileScreen() {
        DonationInfoCard()
    }


    @Composable
    fun DonationInfoCard(
        modifier: Modifier = Modifier
    ) {
        Card(modifier = Modifier
            .size(width = 240.dp, height = 60.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "20.02.2026",
                    style = MaterialTheme.typography.displayMedium
                )
                Icon(
                    painter = painterResource(R.drawable.blood),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(22.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.blood),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }

    @Composable
    @Preview
    fun ProfileScreenPreview() {
        DonorTrackTheme {
            ProfileScreen()
        }
    }
}