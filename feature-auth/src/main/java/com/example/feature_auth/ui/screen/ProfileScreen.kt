package com.example.feature_auth.ui.screen

import android.os.Build
import androidx.annotation.DrawableRes
import com.example.domain.model.donation.DonationType
import com.example.domain.model.donation.DonationStatus
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.feature_auth.R
import com.example.domain.model.Bage
import com.example.domain.model.UserModel
import com.example.domain.model.donation.DonationModel
import com.example.feature_common.ui.theme.BlackHanSans
import com.example.feature_auth.viewmodel.ProfileViewModel
import com.example.feature_auth.utils.toTitleRes
import com.example.feature_common.util.navigation.Routes
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileApp(
    navController: NavController = rememberNavController(),
    viewModel: ProfileViewModel = viewModel(),
    modifier: Modifier = Modifier,
    onLogoutClick: () -> Unit
) {
    val uiState by viewModel.profileUiState.collectAsStateWithLifecycle()

    androidx.compose.runtime.LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (uiState.isLoading && !uiState.isInitialized) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else if (uiState.error != null && !uiState.isInitialized) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Ошибка: ${uiState.error}", color = MaterialTheme.colorScheme.error)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item { UserInfo(navController = navController, userModel = uiState.userModel) }
                item { CountOfDonation(uiState.donations) }
                item { UserBages(uiState.userModel.bages ?: emptyList()) }

                items(uiState.donations) { donation ->
                    DonationInfoCard(donation = donation)
                }

                item {
                    Spacer(modifier = Modifier.size(16.dp))
                    Button(
                        onClick = onLogoutClick,
                        modifier = Modifier.padding(bottom = 32.dp)
                    ) {
                        Text(stringResource(R.string.logout))
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DonationInfoCard(
    modifier: Modifier = Modifier,
    donation: DonationModel
) {
    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val formattedDate = donation.date.format(dateFormatter)

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = modifier.size(width = 240.dp, height = 54.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    DonationIcon(
                        donIcon = getDonationTypeIconRes(donation.donationType)
                    )
                    DonationIcon(
                        donIcon = getDonationStatusIconRes(donation.donationStatus)
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
    Icon(
        painter = painterResource(donIcon),
        contentDescription = null,
        modifier = modifier.size(24.dp),
        tint = Color.Unspecified
    )
}

@Composable
fun UserBages(list: List<Bage>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        list.forEach { BageInfo(it) }
    }
}

@Composable
fun UserData(
    userModel: UserModel,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = userModel.name,
            fontSize = 25.sp,
            fontFamily = BlackHanSans,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = stringResource(userModel.bloodType.toTitleRes()),
            fontSize = 25.sp,
            fontFamily = BlackHanSans,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = userModel.city ?: "Город не указан",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun ProfileButtons(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { navController.navigate(Routes.EditProfile.route) },
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.redactProfile),
                textAlign = TextAlign.Center
            )
        }
        Button(
            onClick = { navController.navigate(Routes.AddDonation.route) },
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
fun UserInfo(navController: NavController, userModel: UserModel) {
    UserData(userModel)
    ProfileButtons(navController)
}

@Composable
fun BageInfo(
    bage: Bage,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Image(
            painter = painterResource(bage.bageIcon),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.size(48.dp)
        )
        Text(
            text = stringResource(bage.bageDescription),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            painter = painterResource(bloodType),
            contentDescription = null,
            modifier = modifier.size(32.dp),
            tint = Color.Unspecified
        )
        Text(
            text = donationCount.toString(),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun CountOfDonation(donations: List<DonationModel>) {
    val bloodCount = donations.count { it.donationType == DonationType.BLOOD }
    val plasmaCount = donations.count { it.donationType == DonationType.PLASMA }
    val thromboCount = donations.count { it.donationType == DonationType.THROMBOCYTE }
    val erythroCount = donations.count { it.donationType == DonationType.ERYTHROCYTE }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TypeOfDonation(R.drawable.blood, bloodCount)
            TypeOfDonation(R.drawable.plasma, plasmaCount)
            TypeOfDonation(R.drawable.thrombocyte, thromboCount)
            TypeOfDonation(R.drawable.erythrocyte, erythroCount)
        }
    }
}

private fun getDonationTypeIconRes(type: DonationType): Int {
    return when (type) {
        DonationType.BLOOD -> R.drawable.blood
        DonationType.PLASMA -> R.drawable.plasma
        DonationType.ERYTHROCYTE -> R.drawable.erythrocyte
        DonationType.THROMBOCYTE -> R.drawable.thrombocyte
    }
}

private fun getDonationStatusIconRes(status: DonationStatus): Int {
    return when (status) {
        DonationStatus.CONFIRMED -> R.drawable.blood
        else -> R.drawable.blood
    }
}