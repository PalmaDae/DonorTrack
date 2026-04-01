package com.example.feature_auth.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.donortrack.R
import com.example.donortrack.ui.theme.DonorTrackTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.donortrack.data.model.UserModel
import com.example.donortrack.util.ServiceLocator
import com.example.donortrack.util.hashpass
import kotlinx.coroutines.launch

@Composable
fun RegisterApp(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputFields()
    }
}






@Composable
fun InputFields(
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordCorrect by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.email)) },
            placeholder = { Text(stringResource(R.string.exampleEmail)) },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
            singleLine = true
        )

        OutlinedTextField(
            value = login,
            onValueChange = { login = it },
            label = { Text(stringResource(R.string.username)) },
            placeholder = { Text(stringResource(R.string.exampleUsername)) },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
            singleLine = true
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password)) },
            placeholder = { Text(stringResource(R.string.examplePassword)) },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
            singleLine = true
        )

        OutlinedTextField(
            value = passwordCorrect,
            onValueChange = { passwordCorrect = it },
            label = { Text(stringResource(R.string.passwordCorrect)) },
            placeholder = { Text(stringResource(R.string.examplePassword)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
        )
        Spacer(
            modifier = Modifier.height(64.dp)
        )

        Button(
            onClick = {
                scope.launch {
                    var userModel = UserModel(login = login, hashPass = hashpass(password), email = email)

                    ServiceLocator.getUserRepository().createNewUser(userModel = userModel)
                }
            }
        ) {
            Text(text = stringResource(R.string.createProfile))
        }
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