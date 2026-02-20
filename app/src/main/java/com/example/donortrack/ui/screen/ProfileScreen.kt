package com.example.donortrack.ui.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
        modifier: Modifier = Modifier,

    ) {
        Card(modifier = Modifier
            .height(50.dp)
            .width(180.dp)
            .fillMaxSize(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
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
    }

    @Composable
    @Preview
    fun ProfileScreenPreview() {
        DonorTrackTheme {
            ProfileScreen()
        }
    }
}