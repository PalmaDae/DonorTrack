package com.example.donortrack.ui.screen

import androidx.annotation.StringRes
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.donortrack.R
import com.example.donortrack.ui.theme.DonorTrackTheme

@Composable
fun MainApp() {
    FactCard(fact = R.string.fact1)
}


@Composable
@Preview
fun MainScreenPreview() {
    DonorTrackTheme {
        MainApp()
    }
}


@Composable
@Preview
fun MainScreenPreviewDark() {
    DonorTrackTheme(darkTheme = true) {
        MainApp()
    }
}




@Composable
fun FactCard(
    modifier: Modifier = Modifier,
    @StringRes fact: Int
) {
    Card {
        Text(
            text = stringResource(fact)
        )
    }
}