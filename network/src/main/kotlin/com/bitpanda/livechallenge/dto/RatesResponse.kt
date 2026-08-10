package com.bitpanda.livechallenge.dto

import kotlinx.serialization.Serializable

@Serializable
data class RatesResponse(
    val data: List<RateDto>
)
