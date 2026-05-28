package com.example.feature_add_donation.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.domain.model.donation.DonationType
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.feature_add_donation.viewmodel.AddDonationViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AddDonationApp(
    modifier: Modifier = Modifier,
    viewModel: AddDonationViewModel = viewModel(),
    onSuccessBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onSuccessBack()
        }
    }

    Box(modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 60.dp, start = 16.dp, end = 16.dp)
        ) {
            AddDonationContent(
                isLoading = uiState.isLoading,
                onSaveClick = { dateMillis, type, uri ->
                    viewModel.createDonation(context, dateMillis, type.name, uri)
                }
            )

            uiState.error?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary // Крутилка в цвет бренда
            )
        }
    }
}

@Composable
fun AddDonationContent(
    isLoading: Boolean,
    onSaveClick: (Long?, DonationType, Uri?) -> Unit
) {
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDonationType by remember { mutableStateOf(DonationType.BLOOD) }
    var showDonationMenu by remember { mutableStateOf(false) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val pickMedia = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri -> selectedImageUri = uri }

    val formattedDate = remember(selectedDate) {
        selectedDate?.let {
            SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date(it))
        } ?: "Дата не выбрана"
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = { showDatePicker = true },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Выбрать дату")
        }


        Text(
            text = formattedDate,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { showDonationMenu = true },
                enabled = !isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Тип: ${selectedDonationType.name}")
            }

            DonationTypePicker(
                expanded = showDonationMenu,
                onDonationChange = { type -> selectedDonationType = type },
                onDismissRequest = { showDonationMenu = false }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (selectedImageUri == null) "Загрузить справку (опционально)" else "Справка прикреплена")
        }

        selectedImageUri?.let {
            Text(
                text = "Файл: ${it.lastPathSegment}",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onSaveClick(selectedDate, selectedDonationType, selectedImageUri) },
            enabled = !isLoading && selectedDate != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Добавить донацию")
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