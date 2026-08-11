package com.buiguesortola.cryptotracker.repository

import com.buiguesortola.cryptotracker.api.KtorCryptoApi
import com.buiguesortola.cryptotracker.domain.CryptoError
import com.buiguesortola.cryptotracker.domain.models.Coin
import com.buiguesortola.cryptotracker.domain.repository.CryptosRepository
import com.buiguesortola.cryptotracker.dto.toCoin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

const val EURO_SYMBOL = "EUR"

class CryptoRepositoryImpl (
    private val api: KtorCryptoApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): CryptosRepository {

    /**
     * Fetches assets and converts their prices to EUR using the current exchange rate.
     *
     * This method coordinates two parallel network requests:
     * 1. Assets data (price in USD, change percentage, etc.)
     * 2. Exchange rates to find the USD/EUR conversion factor.
     *
     * @return A [Result] containing a list of [Coin] domain models.
     * Returns [Result.failure] with an [IllegalStateException] if the Euro rate is not found.
     * This prevents the app from showing
     * incorrect or misleading financial information.
     */
    override suspend fun getCoins(): Result<List<Coin>> = withContext(dispatcher) {
        try {
            // coroutineScope ensures structured concurrency: if one deferred fails,
            // it cancels the other sibling and propagates the exception to the catch block.
            coroutineScope {
                val assetsDeferred = async { api.getAssets() }
                val ratesDeferred = async { api.getRates() }

                val assetsResponse = assetsDeferred.await()
                val ratesResponse = ratesDeferred.await()

                val euroRateUsd = ratesResponse.data
                    .find { rate -> rate.symbol == EURO_SYMBOL }
                    ?.rateUsd?.toDoubleOrNull()
                    ?: return@coroutineScope Result.failure(
                        CryptoError.EuroRateNotFoundError("Euro rate not found")
                    )
                val coins = assetsResponse.data.map { dto ->
                    dto.toCoin(euroRateUsd)
                }
                Result.success(coins)
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            val mappedError = when (e) {
                is kotlinx.io.IOException -> {
                    CryptoError.NetworkError("Check your internet connection: ${e.message}")
                }
                else -> {
                    Exception(e.message)
                }
            }
            Result.failure(mappedError)
        }
    }
}