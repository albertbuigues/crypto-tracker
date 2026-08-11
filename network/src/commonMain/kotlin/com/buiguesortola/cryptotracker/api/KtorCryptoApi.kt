package com.buiguesortola.cryptotracker.api

import com.buiguesortola.cryptotracker.dto.AssetResponse
import com.buiguesortola.cryptotracker.dto.RatesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorCryptoApi(private val httpClient: HttpClient) {

    suspend fun getAssets(): AssetResponse {
        return httpClient.get("assets").body()
    }

    suspend fun getRates(): RatesResponse {
        return httpClient.get("rates").body()
    }
}