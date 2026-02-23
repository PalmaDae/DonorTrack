package com.example.donortrack.ui.screen

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.donortrack.R
import com.example.donortrack.data.model.User
import com.example.donortrack.ui.theme.DonorTrackTheme
import com.example.donortrack.viewmodel.ProfileViewModel

@Composable
fun EditProfileApp(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel()
) {
    val viewModel by viewModel.profileUiState.collectAsState()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        EditButtons(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 60.dp),
            user = viewModel.user
        )

        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Text(text = stringResource(R.string.saveProfile))
        }
    }
}

@Composable
fun EditButtons(
    modifier: Modifier = Modifier,
    user: User
) {
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape)
                .clickable {  }
        ) {
            Image(
                user.avatarUri?.let { rememberAsyncImagePainter(user.avatarUri) } ?: painterResource(user.avatar),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = user.name
        )
        Button(onClick = {}) {
            Text(
                text = stringResource(R.string.changeName)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {}) {
            Text(
                text = stringResource(R.string.changeBloodType)
            )
        }
    }
}

@Composable
fun ChangeNameButton() {

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