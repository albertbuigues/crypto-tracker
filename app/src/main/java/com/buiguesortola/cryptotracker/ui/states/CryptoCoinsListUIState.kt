package com.buiguesortola.cryptotracker.ui.states

import com.buiguesortola.cryptotracker.domain.CoinFilter
import com.buiguesortola.cryptotracker.domain.models.Coin

data class CryptoCoinsListUIState(
    val selectedChip: CoinFilter = CoinFilter.TOP_TEN,
    val coinsList: List<CoinUiState> = emptyList()
)

data class CoinUiState(
    val name: String,
    val symbol: String,
    val priceInEuro: Double,
    val changePercentage: String
)

fun Coin.toUiState(): CoinUiState = CoinUiState(
    name = this.name,
    symbol = this.symbol,
    priceInEuro = this.priceInEuro,
    changePercentage = this.changePercentFormatted
)
