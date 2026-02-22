package com.example.donortrack.ui.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.donortrack.R
import com.example.donortrack.ui.theme.DonorTrackTheme

@Composable
fun MainApp() {
    val items = stringArrayResource(id = R.array.facts_quotes)

    val randomItem = items.random()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { FactCard(fact = randomItem) }
    }
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
    fact: String
) {
    Card(
        modifier = modifier
            .size(width = (250.dp), height = 85.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = fact,
                textAlign = TextAlign.Center
            )
        }
    }

}