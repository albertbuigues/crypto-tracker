package com.bitpanda.livechallenge.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bitpanda.livechallenge.R
import com.bitpanda.livechallenge.domain.EMPTY_SCREEN_BTN_TAG
import com.bitpanda.livechallenge.domain.EMPTY_SCREEN_TAG
import com.bitpanda.livechallenge.ui.theme.BackgroundPrimaryColor
import com.bitpanda.livechallenge.ui.theme.BitpandaLiveChallengeTheme

@Composable
fun StatefulEmptyScreen(
    helperText: String,
    onRefresh: () -> Unit
) {
    StatelessEmptyScreen(emptyScreenText = helperText, onRefresh = onRefresh)
}

@Composable
private fun StatelessEmptyScreen(
    emptyScreenText: String = "No data available. Swipe down to reload data.",
    onRefresh: () -> Unit = {}
) {
    val refreshButtonText = stringResource(R.string.refresh)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .testTag(EMPTY_SCREEN_TAG),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.warning_image),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = emptyScreenText,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = Color.White
            )
        }
        Button(
            onClick = onRefresh,
            border = BorderStroke(2.dp, Color.White),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .testTag(EMPTY_SCREEN_BTN_TAG)
        ) {
            Text(
                text = refreshButtonText
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun PreviewEmptyScreen() {
    BitpandaLiveChallengeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundPrimaryColor
        ) {
            StatelessEmptyScreen()
        }
    }
}