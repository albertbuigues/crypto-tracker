package com.bitpanda.livechallenge.ui.states

import com.bitpanda.livechallenge.domain.TOP_TEN_FILTER
import com.bitpanda.livechallenge.domain.models.Coin

data class CryptoCoinsListUIState(
    val selectedChip: Byte = TOP_TEN_FILTER,
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
