package com.buiguesortola.cryptotracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import com.buiguesortola.cryptotracker.domain.LOADING_TAG
import com.buiguesortola.cryptotracker.ui.theme.BackgroundPrimaryColor
import com.buiguesortola.cryptotracker.ui.theme.BitpandaLiveChallengeTheme

@androidx.compose.runtime.Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Transparent).testTag(LOADING_TAG),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            trackColor = Color.Transparent,
            color = Color.White
        )
    }
}

@androidx.compose.ui.tooling.preview.Preview
@androidx.compose.runtime.Composable
private fun PreviewLoadingScreen() {
    BitpandaLiveChallengeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundPrimaryColor
        ) {
            LoadingScreen()
        }
    }
}