package com.example.donortrack.ui.screen

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.donortrack.R
import com.example.donortrack.data.Bage
import com.example.donortrack.data.Donation
import com.example.donortrack.data.bages
import com.example.donortrack.data.donations
import com.example.donortrack.ui.theme.DonorTrackTheme

class ProfileScreen {



    @Composable
    fun ProfileApp() {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                UserInfo()
                UserBages()
                Spacer(modifier = Modifier.weight(2f))
                DonationList(donations as MutableList<Donation>)
            }
        }
    }


    @Composable
    fun DonationInfoCard(
        modifier: Modifier = Modifier,
        donation: Donation
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Card(modifier = modifier
                .size(width = (200.dp), height = 45.dp),
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
                            text = stringResource(donation.dateOfDonation),
                            style = MaterialTheme.typography.displayMedium
                        )
                        DonationIcon(
                            donIcon = donation.typeOfDonation
                        )
                        DonationIcon(
                            donIcon = donation.stageOfDonation
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun DonationIcon(
        @DrawableRes donIcon: Int,
        modifier: Modifier = Modifier
    ) {
        Image(
            modifier = modifier,
            contentScale = ContentScale.Crop,
            painter = painterResource(donIcon),
            contentDescription = null,
        )
    }

    @Composable
    fun DonationList(
        list: MutableList<Donation>
    )
    {
        LazyColumn {
            items(list) {
                DonationInfoCard(
                    donation = it,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }



    @Composable
    fun UserBages(
        
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BageInfo(bage = bages[0])
            BageInfo(bage = bages[1])
            BageInfo(bage = bages[2])
        }
    }

    @Composable
    fun UserInfo() {

    }

    @Composable
    fun BageInfo(
        bage: Bage,
        modifier: Modifier = Modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = modifier,
                painter = painterResource(bage.bageIcon),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(bage.bageDescription)
            )
        }
    }


    @Composable
    @Preview
    fun ProfileScreenPreviewDark() {
        DonorTrackTheme(darkTheme = true) {
            ProfileApp()
        }
    }

    @Composable
    @Preview
    fun ProfileScreenPreview() {
        DonorTrackTheme {
            ProfileApp()
        }
    }
}