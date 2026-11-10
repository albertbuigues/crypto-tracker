package com.buiguesortola.cryptotracker.domain.models

import java.math.BigDecimal

/**
 * Represents a coin asset with its market data for the UI.
 *
 * @property id Unique identifier (e.g., "bitcoin").
 * @property name Full name of the coin (e.g., "Bitcoin").
 * @property symbol Currency symbol (e.g., "BTC").
 * @property priceInEuro Current price converted to Euros.
 * @property changePercent Numeric 24h change for sorting purposes.
 */
data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val priceInEuro: BigDecimal,
    val changePercent: Double
)
