package com.buiguesortola.cryptotracker.dto

import com.buiguesortola.cryptotracker.domain.models.Coin
import kotlinx.serialization.Serializable
import kotlin.math.round

@Serializable
data class AssetDto(
    val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: String,
    val changePercent24Hr: String,
)

/**
 * Maps an Asset DTO object to a Domain Model ([Coin]).
 *
 * @param euroConversionRateToUsd The current conversion rate (how many USD per 1 EUR).
 * * Performs the currency conversion from USD to EUR and formats the 24h change
 * percentage to a two-decimal string (e.g., "1.23%") as required by the README
 * specifications.
 */
fun AssetDto.toCoin(euroConversionRateToUsd: Double): Coin {
    val usdPrice = priceUsd.toDoubleOrNull() ?: 0.0
    val percent = changePercent24Hr.toDoubleOrNull() ?: 0.0
    val priceInEur = usdPrice / euroConversionRateToUsd
    val roundedPercent = round(percent * 100) / 100

    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        priceInEuro = priceInEur,
        changePercent = percent,
        changePercentFormatted = "$roundedPercent%"
    )
}