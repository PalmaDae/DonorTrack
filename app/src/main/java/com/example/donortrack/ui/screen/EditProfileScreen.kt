package com.example.donortrack.ui.screen

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.donortrack.R
import com.example.donortrack.data.model.BloodType
import com.example.donortrack.data.model.User
import com.example.donortrack.state.ProfileUiState
import com.example.donortrack.ui.theme.DonorTrackTheme
import com.example.donortrack.viewmodel.ProfileViewModel

@Composable
fun EditProfileApp(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel()
) {
    val uiState by viewModel.profileUiState.collectAsState()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        EditButtons(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 60.dp),
            uiState = uiState,
            onNameChange = viewModel::nameUpdate,
            onAvatarChange = viewModel::avatarUpdate,
            onBloodChange = viewModel::bloodTypeUpdate
        )
    }
}

@Composable
fun EditButtons(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState,
    onNameChange: (String) -> Unit,
    onAvatarChange: (String) -> Unit,
    onBloodChange: (BloodType) -> Unit
) {
    var showBloodDropDown by remember { mutableStateOf(false) }
    var nameNow by remember { mutableStateOf(uiState.user.name) }
    val pickMedia = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let {
            onAvatarChange(it.toString())
        }
    }

    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape)
                .clickable { pickMedia.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
                }
        ) {
            Image(
                uiState.user.avatarUri?.let { rememberAsyncImagePainter(uiState.user.avatarUri) } ?: painterResource(uiState.user.avatar),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = uiState.user.name
        )
        ChangeNameButton(nameNow = nameNow, onNameChange = { newName ->
            nameNow = newName
            onNameChange(newName)
        })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { showBloodDropDown = true }) {
            Text(text = stringResource(uiState.user.bloodType.titleRes))
        }

        DropDownBlood(
            expanded = showBloodDropDown,
            onBloodChange = onBloodChange,
            onDismissRequest = { showBloodDropDown = false }
        )

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
                text = { Text ( stringResource(type.titleRes)) },
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