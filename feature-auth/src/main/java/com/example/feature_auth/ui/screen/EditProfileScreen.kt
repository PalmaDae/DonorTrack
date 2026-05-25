package com.example.feature_auth.ui.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.domain.model.BloodType
import com.example.feature_auth.state.ProfileUiState
import com.example.feature_common.ui.theme.DonorTrackTheme
import com.example.feature_auth.viewmodel.ProfileViewModel
import com.example.feature_auth.R
import com.example.feature_auth.utils.toTitleRes

@Composable
fun EditProfileApp(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel()
) {
    val uiState by viewModel.profileUiState.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        EditButtons(
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 60.dp),
            uiState = uiState,
            onBloodChange = { newBlood -> viewModel.updateBloodType(newBlood.name) },
            onEmailChange = { newEmail -> viewModel.updateEmail(newEmail) },
            onCityChange = { newCity -> viewModel.updateCity(newCity) },
            onPasswordChange = { old, new, confirm -> viewModel.updatePassword(old, new, confirm) }
        )
    }
}

@Composable
fun EditButtons(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState,
    onBloodChange: (BloodType) -> Unit,
    onEmailChange: (String) -> Unit,
    onCityChange: (String) -> Unit,
    onPasswordChange: (String, String, String) -> Unit // (old, new, confirm)
) {
    var emailInput by remember { mutableStateOf(uiState.userModel.email) }
    var cityInput by remember { mutableStateOf(uiState.userModel.city ?: "") }
    var oldPass by remember { mutableStateOf("") }
    var newPass by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }
    var showBloodDropDown by remember { mutableStateOf(false) }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

        OutlinedTextField(value = emailInput, onValueChange = { emailInput = it }, label = { Text("Email") })
        Button(onClick = { onEmailChange(emailInput) }) { Text("Сохранить Email") }

        OutlinedTextField(value = cityInput, onValueChange = { cityInput = it }, label = { Text("Город") })
        Button(onClick = { onCityChange(cityInput) }) { Text("Сохранить город") }

        OutlinedTextField(value = oldPass, onValueChange = { oldPass = it }, label = { Text("Старый пароль") })
        OutlinedTextField(value = newPass, onValueChange = { newPass = it }, label = { Text("Новый пароль") })
        OutlinedTextField(value = confirmPass, onValueChange = { confirmPass = it }, label = { Text("Подтверждение") })
        Button(onClick = { onPasswordChange(oldPass, newPass, confirmPass) }) { Text("Сменить пароль") }

        Button(onClick = { showBloodDropDown = true }) {
            Text(text = "Группа: ${uiState.userModel.bloodType}")
        }
        DropDownBlood(expanded = showBloodDropDown, onBloodChange = onBloodChange, onDismissRequest = { showBloodDropDown = false })
    }
}

@Composable
fun DropDownBlood(
    expanded: Boolean,
    onBloodChange: (BloodType) -> Unit,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        offset = DpOffset(x = 0.dp, y = 0.dp)
    ) {
        BloodType.values().forEach { type ->
            DropdownMenuItem(
                text = { Text(stringResource(type.toTitleRes())) },
                onClick = {
                    onBloodChange(type)
                    onDismissRequest()
                }
            )
        }
    }
}

@Composable
fun ChangeNameButton(
    nameNow: String,
    onNameChange: (String) -> Unit
) {
    OutlinedTextField(
        value = nameNow,
        label = { Text("Name") },
        onValueChange = { onNameChange(it) },
        singleLine = true
    )
}

@Composable
@Preview
fun EditProfilePreview() {
    DonorTrackTheme {
        EditProfileApp()
    }
}

@Composable
@Preview
fun EditProfilePreviewDark() {
    DonorTrackTheme(darkTheme = true) {
        EditProfileApp()
    }
}