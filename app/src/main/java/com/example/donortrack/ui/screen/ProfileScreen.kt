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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.donortrack.R
import com.example.donortrack.data.Bage
import com.example.donortrack.data.Donation
import com.example.donortrack.data.User
import com.example.donortrack.data.bages
import com.example.donortrack.data.donations
import com.example.donortrack.data.testUser
import com.example.donortrack.ui.theme.BlackHanSans
import com.example.donortrack.ui.theme.DonorTrackTheme





@Composable
fun ProfileApp() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { UserInfo() }
        item { UserBages(bages as MutableList<Bage>) }
        item { Spacer(modifier = Modifier.height(16.dp)) }

        items(donations) {
            DonationInfoCard(
                donation = it,
                modifier = Modifier.padding(8.dp)
            )
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

//Передавать данные для бейджа, а так картинки статичные, или сделать выбор значков в профиле
@Composable
fun UserBages(
    list: MutableList<Bage>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        list.forEach { BageInfo(it) }
    }
}

@Composable
fun UserData(
    user: User,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(user.avatar),
            contentDescription = null,
            modifier = modifier
                .padding(10.dp)
                .clip(CircleShape)
                .size(190.dp)
        )
        Text(
            text = stringResource(user.name),
            fontSize = 25.sp,
            fontFamily = BlackHanSans
        )
        Text(
            text = stringResource(user.bloodType),
            fontSize = 25.sp,
            fontFamily = BlackHanSans
        )
    }
}

@Composable
fun ProfileButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {},
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.redactProfile),
                textAlign = TextAlign.Center
            )
        }
        Button(
            onClick = {},
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.addDonation),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun UserInfo() {
    UserData(testUser)
    ProfileButtons()
    CountOfDonation()
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
fun TypeOfDonation(
    @DrawableRes bloodType: Int,
    donationCount: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(bloodType),
            contentDescription = null
        )
        Text(text = donationCount.toString())
    }
}

//Оставить иконки у всех одинаковые, передавать чисто кол-во донаций для каждого типа

@Composable
fun CountOfDonation() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TypeOfDonation(R.drawable.blood, 3)
        TypeOfDonation(R.drawable.plasma, 2)
        TypeOfDonation(R.drawable.thrombocyte, 5)
        TypeOfDonation(R.drawable.erythrocyte, 0)
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
