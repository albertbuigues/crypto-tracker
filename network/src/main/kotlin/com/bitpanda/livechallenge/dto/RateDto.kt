package com.bitpanda.livechallenge.dto

import kotlinx.serialization.Serializable

@Serializable
data class RateDto(
    val id: String,
    val symbol: String,
    val rateUsd: String
)
