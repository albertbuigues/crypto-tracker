package com.bitpanda.livechallenge.api

import com.bitpanda.livechallenge.dto.AssetResponse
import com.bitpanda.livechallenge.dto.RatesResponse
import retrofit2.http.GET

interface CryptoApi {

    @GET("assets")
    suspend fun getAssets(): AssetResponse

    @GET("rates")
    suspend fun getRates(): RatesResponse
}