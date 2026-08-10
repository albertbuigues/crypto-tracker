package com.buiguesortola.cryptotracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.buiguesortola.cryptotracker.ui.screens.CryptoCoinsScreen
import com.buiguesortola.cryptotracker.ui.theme.BackgroundPrimaryColor
import com.buiguesortola.cryptotracker.ui.theme.BitpandaLiveChallengeTheme
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