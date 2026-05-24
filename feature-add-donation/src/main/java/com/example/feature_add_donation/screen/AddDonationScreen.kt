package com.example.feature_add_donation.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.domain.model.donation.DonationType
import com.example.feature_common.ui.theme.DonorTrackTheme

@Composable
fun AddDonationApp(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AddDonationContent(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 60.dp, start = 16.dp, end = 16.dp)
        )
    }
}

@Composable
fun AddDonationContent(
    modifier: Modifier = Modifier
) {
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDonationType by remember { mutableStateOf(DonationType.BLOOD) }
    var showDonationMenu by remember { mutableStateOf(false) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val pickMedia = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let {
            selectedImageUri = it
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { showDatePicker = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Выбрать дату")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = selectedDate?.toString() ?: "Дата не выбрана"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { showDonationMenu = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(selectedDonationType.name)
            }

            DonationTypePicker(
                expanded = showDonationMenu,
                onDonationChange = { type -> selectedDonationType = type },
                onDismissRequest = { showDonationMenu = false }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                pickMedia.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Загрузить справку")
        }

        Spacer(modifier = Modifier.height(8.dp))

        selectedImageUri?.let {
            Text("Файл выбран")
        }
    }

    if (showDatePicker) {
        DatePickerModal(
            onDateSelected = { date -> selectedDate = date },
            onDismiss = { showDatePicker = false }
        )
    }
}

@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
fun DonationTypePicker(
    expanded: Boolean,
    onDonationChange: (DonationType) -> Unit,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        offset = DpOffset(x = 0.dp, y = 0.dp)
    ) {
        DonationType.values().forEach { type ->
            DropdownMenuItem(
                text = { Text(type.name) },
                onClick = {
                    onDonationChange(type)
                    onDismissRequest()
                }
            )
        }
    }
}

@Preview
@Composable
fun AddDonationPreview() {
    DonorTrackTheme {
        AddDonationApp()
    }
}

@Preview
@Composable
fun AddDonationPreviewDark() {
    DonorTrackTheme(darkTheme = true) {
        AddDonationApp()
    }
}