package com.example.feature_add_donation.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.domain.model.DonationType
import com.example.feature_add_donation.viewmodel.AddDonationViewModel
import com.example.feature_add_donation.R

@Composable
fun AddDonationScreen(
    modifier: Modifier = Modifier,
    viewModel: AddDonationViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val context = androidx.compose.ui.platform.LocalContext.current
    val contentResolver = context.contentResolver

    var expanded by remember { mutableStateOf(false) }

    val pickMedia = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let {
            viewModel.onCertificateChange(it.toString())
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Add Donation",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = uiState.date,
            onValueChange = viewModel::onDateChange,
            label = { Text("Donation Date") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { expanded = true }) {
            Text(uiState.type.name)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DonationType.values().forEach { type ->
                DropdownMenuItem(
                    text = { Text(type.name) },
                    onClick = {
                        viewModel.onTypeChange(type)
                        expanded = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    pickMedia.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            val painter = if (!uiState.certificateUri.isNullOrEmpty()) {
                rememberAsyncImagePainter(uiState.certificateUri)
            } else {
                painterResource(android.R.drawable.ic_menu_upload)
            }

            Image(
                painter = painter,
                contentDescription = "Certificate",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(80.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.submitDonation(contentResolver)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Donation")
        }
    }
}