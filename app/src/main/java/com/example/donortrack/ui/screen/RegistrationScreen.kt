package com.example.donortrack.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.donortrack.ui.theme.DonorTrackTheme

@Composable
fun RegisterApp() {

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