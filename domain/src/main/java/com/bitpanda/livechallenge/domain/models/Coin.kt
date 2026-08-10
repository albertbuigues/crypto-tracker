package com.bitpanda.livechallenge.domain.models

/**
 * Represents a coin asset with its market data for the UI.
 *
 * @property id Unique identifier (e.g., "bitcoin").
 * @property name Full name of the coin (e.g., "Bitcoin").
 * @property symbol Currency symbol (e.g., "BTC").
 * @property priceInEuro Current price converted to Euros.
 * @property changePercent Numeric 24h change for sorting purposes.
 * @property changePercentFormatted String representation of change (format: "xx.xx%").
 */
data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val priceInEuro: Double,
    val changePercent: Double,
    val changePercentFormatted: String
)
