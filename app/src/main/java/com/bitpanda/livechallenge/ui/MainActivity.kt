package com.bitpanda.livechallenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bitpanda.livechallenge.ui.screens.CryptoCoinsScreen
import com.bitpanda.livechallenge.ui.theme.BackgroundPrimaryColor
import com.bitpanda.livechallenge.ui.theme.BitpandaLiveChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BitpandaLiveChallengeTheme {
                Scaffold(
                    containerColor = BackgroundPrimaryColor
                ) { padding ->
                    Surface(
                        modifier = Modifier.padding(padding).padding(horizontal = 16.dp),
                        color = Color.Transparent
                    ) {
                        CryptoCoinsScreen()
                    }
                }
            }
        }
    }
}