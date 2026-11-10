package com.buiguesortola.cryptotracker.ui.states

import com.buiguesortola.cryptotracker.domain.CoinFilter
import com.buiguesortola.cryptotracker.domain.models.Coin
import java.math.BigDecimal

data class CryptoCoinsListUIState(
    val selectedChip: CoinFilter = CoinFilter.TOP_TEN,
    val coinsList: List<CoinUiState> = emptyList()
)

data class CoinUiState(
    val name: String,
    val symbol: String,
    val priceInEuro: BigDecimal,
    val changePercent: Double
)

fun Coin.toUiState(): CoinUiState = CoinUiState(
    name = this.name,
    symbol = this.symbol,
    priceInEuro = this.priceInEuro,
    changePercent = this.changePercent
)
