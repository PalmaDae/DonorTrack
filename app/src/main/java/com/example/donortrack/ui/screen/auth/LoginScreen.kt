package com.example.donortrack.ui.screen.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.donortrack.R
import com.example.donortrack.ui.theme.DonorTrackTheme

@Composable
fun LoginApp(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        InputFieldsLogin()

        Spacer(
            modifier = Modifier.height(64.dp)
        )

        Button(
            onClick = {}
        ) {
            Text(text = stringResource(R.string.login))
        }
    }
}



@Composable
fun InputFieldsLogin(
    modifier: Modifier = Modifier
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = modifier) {
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
fun LoginPreview() {
    DonorTrackTheme {
        LoginApp()
    }
}

@Composable
@Preview
fun LoginPreviewDark() {
    DonorTrackTheme(
        darkTheme = true
    ) {
        LoginApp()
    }
}