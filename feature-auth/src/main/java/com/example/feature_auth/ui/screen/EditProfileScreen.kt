package com.example.feature_auth.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.model.BloodType
import com.example.feature_auth.R
import com.example.feature_auth.state.ProfileUiState
import com.example.feature_auth.viewmodel.ProfileViewModel

@Composable
fun EditProfileApp(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel(),
    onSuccessBack: () -> Unit
) {
    val uiState by viewModel.profileUiState.collectAsStateWithLifecycle()
    var isSaving by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    LaunchedEffect(uiState.isLoading, uiState.error) {
        if (isSaving && !uiState.isLoading) {
            if (uiState.error == null) {
                onSuccessBack()
            } else {
                isSaving = false
            }
        }
    }


    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                EditButtons(
                    uiState = uiState,
                    isEnabled = !uiState.isLoading,
                    onBloodChange = { bloodType ->
                        isSaving = true
                        viewModel.updateBloodType(bloodType.name)
                    },
                    onCityChange = { newCity ->
                        isSaving = true
                        viewModel.updateCity(newCity)
                    }
                )

                uiState.error?.let { errorMessage ->
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }

            if (uiState.isLoading && !uiState.isInitialized) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(enabled = false) {},
                    contentAlignment = Alignment.Center
                ) {

                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}

@Composable
fun EditButtons(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState,
    isEnabled: Boolean = true,
    onBloodChange: (BloodType) -> Unit,
    onCityChange: (String) -> Unit
) {
    var cityInput by remember { mutableStateOf("") }
    var showBloodDropDown by remember { mutableStateOf(false) }
    var selectedBloodType by remember { mutableStateOf<BloodType?>(null) }

    LaunchedEffect(uiState.isInitialized) {
        if (uiState.isInitialized) {
            cityInput = uiState.userModel.city ?: ""
            selectedBloodType = uiState.userModel.bloodType
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = cityInput,
            onValueChange = { cityInput = it },
            label = { Text("Город проживания") },
            enabled = isEnabled,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { onCityChange(cityInput) },
            enabled = isEnabled && cityInput.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Сохранить город")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectedBloodType?.let { BloodType.toString(it) } ?: "Выберите группу крови",
                onValueChange = {},
                label = { Text(stringResource(R.string.blood_group)) },
                readOnly = true,
                enabled = isEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(enabled = isEnabled) { showBloodDropDown = true }
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable(enabled = isEnabled) { showBloodDropDown = true }
            )

            DropDownBlood(
                expanded = showBloodDropDown,
                onBloodSelected = { type ->
                    selectedBloodType = type
                    onBloodChange(type)
                },
                onDismissRequest = { showBloodDropDown = false }
            )
        }
    }
}

@Composable
fun DropDownBlood(
    expanded: Boolean,
    onBloodSelected: (BloodType) -> Unit,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        offset = DpOffset(x = 0.dp, y = 4.dp)
    ) {
        BloodType.entries.forEach { type ->
            DropdownMenuItem(
                text = { Text(BloodType.toString(type)) },
                onClick = {
                    onBloodSelected(type)
                    onDismissRequest()
                }
            )
        }
    }
}