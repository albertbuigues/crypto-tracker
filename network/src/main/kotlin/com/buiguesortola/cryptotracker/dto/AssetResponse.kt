package com.buiguesortola.cryptotracker.dto

import kotlinx.serialization.Serializable

@Serializable
data class AssetResponse(
    val data: List<AssetDto>
)
