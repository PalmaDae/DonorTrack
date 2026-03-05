package com.example.donortrack.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.donortrack.R
import com.example.donortrack.ui.theme.DonorTrackTheme

@Composable
fun RegisterApp() {
    InputFields()
}






@Composable
fun InputFields(
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.email)) },
            placeholder = { Text(stringResource(R.string.exampleEmail)) }
        )

        OutlinedTextField(
            value = login,
            onValueChange = { login = it },
            label = { Text(stringResource(R.string.username)) },
            placeholder = { Text(stringResource(R.string.exampleUsername)) }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password)) },
            placeholder = { Text(stringResource(R.string.examplePassword)) }
        )
    }
}





@Composable
@Preview
fun RegisterPreview() {
    DonorTrackTheme {
        RegisterApp()
    }
}


@Composable
@Preview
fun RegisterPreviewDark() {
    DonorTrackTheme(
        darkTheme = true
    ) {
        RegisterApp()
    }
}