package com.buiguesortola.cryptotracker.dto

import com.buiguesortola.cryptotracker.domain.models.Coin
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

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
 * Performs the currency conversion from USD to EUR using BigDecimal for precision.
 */
fun AssetDto.toCoin(euroConversionRateToUsd: Double): Coin {
    val usdPrice = priceUsd.toBigDecimalOrNull() ?: BigDecimal.ZERO
    val percent = changePercent24Hr.toDoubleOrNull() ?: 0.0
    val rate = BigDecimal.valueOf(euroConversionRateToUsd)
    val mc = MathContext(8, RoundingMode.HALF_UP)
    val priceInEur = usdPrice.divide(rate, mc)

    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        priceInEuro = priceInEur,
        changePercent = percent
    )
}