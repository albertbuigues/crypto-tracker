@file:OptIn(ExperimentalMaterial3Api::class)

package com.bitpanda.livechallenge.ui.screens

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
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bitpanda.livechallenge.R
import com.bitpanda.livechallenge.domain.FILTER_TOP_TAG
import com.bitpanda.livechallenge.domain.FILTER_WORST_TAG
import com.bitpanda.livechallenge.domain.TOP_TEN_FILTER
import com.bitpanda.livechallenge.domain.WORST_TEN_FILTER
import com.bitpanda.livechallenge.ui.components.StatefulCryptoCoinsListElement
import com.bitpanda.livechallenge.ui.components.StatefulFilterChip
import com.bitpanda.livechallenge.ui.states.CoinUiState
import com.bitpanda.livechallenge.ui.theme.BackgroundPrimaryColor
import com.bitpanda.livechallenge.ui.theme.BitpandaLiveChallengeTheme

@Composable
fun StatefulCryptoCoinsList(
    selectedChip: Byte,
    onChipSelected: (Byte) -> Unit,
    coinsList: List<CoinUiState>
) {
    StatelessCryptoCoinsList(selectedChip, onChipSelected, coinsList)
}

@Composable
private fun StatelessCryptoCoinsList(
    selectedChip: Byte = 0,
    onChipSelected: (Byte) -> Unit = {},
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
                selected = selectedChip == TOP_TEN_FILTER,
                onClick = onChipSelected,
                id = TOP_TEN_FILTER,
                text = stringResource(R.string.best_coins),
                leadingIconResId = R.drawable.arrow_up,
                testTag = FILTER_TOP_TAG
            )
            Spacer(Modifier.width(8.dp))
            StatefulFilterChip(
                selected = selectedChip == WORST_TEN_FILTER,
                onClick = onChipSelected,
                id = WORST_TEN_FILTER,
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
                    priceInEuro = coin.priceInEuro.toString(),
                    changePercentage = coin.changePercentage
                )
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCoinsList() {
    BitpandaLiveChallengeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundPrimaryColor
        ) {
            StatelessCryptoCoinsList(
                coinsList = listOf(
                    CoinUiState("Bitcoin", "BTC", 52000.20, "0.25"),
                    CoinUiState("Bitcoin", "BTC", 52000.20, "0.25"),
                    CoinUiState("Bitcoin", "BTC", 52000.20, "0.25"),
                    CoinUiState("Bitcoin", "BTC", 52000.20, "0.25"),
                    CoinUiState("Bitcoin", "BTC", 52000.20, "0.25"),
                    CoinUiState("Bitcoin", "BTC", 52000.20, "0.25"),
                    CoinUiState("Bitcoin", "BTC", 52000.20, "0.25"),
                    CoinUiState("Bitcoin", "BTC", 52000.20, "0.25"),
                    CoinUiState("Bitcoin", "BTC", 52000.20, "0.25"),
                    CoinUiState("Bitcoin", "BTC", 52000.20, "0.25")
                )
            )
        }
    }
}