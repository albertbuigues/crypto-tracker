package com.bitpanda.livechallenge.domain.usecases

import com.bitpanda.livechallenge.domain.repository.CryptosRepository
import javax.inject.Inject

/**
 * Retrieves the top ten best coins from the repository.
 *
 */
class GetTopTenBestCoinsUseCase @Inject constructor(private val repository: CryptosRepository) {
    suspend operator fun invoke() = repository.getCoins().map { coins ->
        coins.sortedByDescending { coin -> coin.changePercent }.take(10)
    }
}

/**
 * Retrieves the top ten worst coins from the repository.
 *
 */
class GetTopTenWorstCoinsUseCase @Inject constructor(private val repository: CryptosRepository) {
    suspend operator fun invoke() = repository.getCoins().map { coins ->
        coins.sortedBy{ coin -> coin.changePercent }.take(10)
    }
}