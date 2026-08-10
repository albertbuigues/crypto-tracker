package com.bitpanda.livechallenge.dto

import kotlinx.serialization.Serializable

@Serializable
data class AssetResponse(
    val data: List<AssetDto>
)
