package com.buiguesortola.cryptotracker.api

import com.buiguesortola.cryptotracker.dto.AssetResponse
import com.buiguesortola.cryptotracker.dto.RatesResponse
import retrofit2.http.GET

interface CryptoApi {

    @GET("assets")
    suspend fun getAssets(): AssetResponse

    @GET("rates")
    suspend fun getRates(): RatesResponse
}