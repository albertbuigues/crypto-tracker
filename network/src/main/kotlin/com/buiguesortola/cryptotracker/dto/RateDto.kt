package com.buiguesortola.cryptotracker.dto

import kotlinx.serialization.Serializable

@Serializable
data class RateDto(
    val id: String,
    val symbol: String,
    val rateUsd: String,
    val currencySymbol: String? = null,
    val type: String? = null
)
