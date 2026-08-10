package com.bitpanda.livechallenge.repository

import com.bitpanda.livechallenge.api.CryptoApi
import com.bitpanda.livechallenge.domain.CryptoError
import com.bitpanda.livechallenge.domain.models.Coin
import com.bitpanda.livechallenge.domain.repository.CryptosRepository
import com.bitpanda.livechallenge.dto.toCoin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

const val EURO_SYMBOL = "EUR"

class CryptoRepositoryImpl @Inject constructor(
    private val api: CryptoApi,
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
                is IOException -> {
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