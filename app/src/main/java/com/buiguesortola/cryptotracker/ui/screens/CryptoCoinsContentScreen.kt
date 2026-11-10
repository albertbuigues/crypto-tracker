@file:OptIn(ExperimentalMaterial3Api::class)

package com.buiguesortola.cryptotracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buiguesortola.cryptotracker.R
import com.buiguesortola.cryptotracker.domain.CoinFilter
import com.buiguesortola.cryptotracker.testtags.FILTER_TOP_TAG
import com.buiguesortola.cryptotracker.testtags.FILTER_WORST_TAG
import com.buiguesortola.cryptotracker.ui.components.StatefulCryptoCoinsListElement
import com.buiguesortola.cryptotracker.ui.components.StatefulFilterChip
import com.buiguesortola.cryptotracker.ui.states.CoinUiState
import com.buiguesortola.cryptotracker.ui.theme.BackgroundPrimaryColor
import com.buiguesortola.cryptotracker.ui.theme.CryptoTrackerTheme
import java.math.BigDecimal

@Composable
fun StatefulCryptoCoinsList(
    selectedChip: CoinFilter,
    onChipSelected: (CoinFilter) -> Unit,
    coinsList: List<CoinUiState>
) {
    StatelessCryptoCoinsList(selectedChip, onChipSelected, coinsList)
}

@Composable
private fun StatelessCryptoCoinsList(
    selectedChip: CoinFilter = CoinFilter.TOP_TEN,
    onChipSelected: (CoinFilter) -> Unit = {},
    coinsList: List<CoinUiState> = emptyList()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Spacer(Modifier.height(20.dp))
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            StatefulFilterChip(
                selected = selectedChip == CoinFilter.TOP_TEN,
                onClick = onChipSelected,
                id = CoinFilter.TOP_TEN,
                text = stringResource(R.string.best_coins),
                leadingIconResId = R.drawable.arrow_up,
                testTag = FILTER_TOP_TAG
            )
            Spacer(Modifier.width(8.dp))
            StatefulFilterChip(
                selected = selectedChip == CoinFilter.WORST_TEN,
                onClick = onChipSelected,
                id = CoinFilter.WORST_TEN,
                text = stringResource(R.string.worst_coins),
                leadingIconResId = R.drawable.arrow_down,
                testTag = FILTER_WORST_TAG
            )
        }
        Spacer(Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(coinsList) { coin ->
                StatefulCryptoCoinsListElement(
                    coinName = coin.name,
                    symbol = coin.symbol,
                    priceInEuro = coin.priceInEuro,
                    changePercent = coin.changePercent
                )
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCoinsList() {
    CryptoTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundPrimaryColor
        ) {
            StatelessCryptoCoinsList(
                coinsList = listOf(
                    CoinUiState("Bitcoin", "BTC", BigDecimal("52000.20"), 0.25),
                    CoinUiState("Bitcoin", "BTC", BigDecimal("52000.20"), 0.25),
                    CoinUiState("Bitcoin", "BTC", BigDecimal("52000.20"), 0.25),
                    CoinUiState("Bitcoin", "BTC", BigDecimal("52000.20"), 0.25),
                    CoinUiState("Bitcoin", "BTC", BigDecimal("52000.20"), 0.25),
                    CoinUiState("Bitcoin", "BTC", BigDecimal("52000.20"), 0.25),
                    CoinUiState("Bitcoin", "BTC", BigDecimal("52000.20"), 0.25),
                    CoinUiState("Bitcoin", "BTC", BigDecimal("52000.20"), 0.25),
                    CoinUiState("Bitcoin", "BTC", BigDecimal("52000.20"), 0.25)
                )
            )
        }
    }
}