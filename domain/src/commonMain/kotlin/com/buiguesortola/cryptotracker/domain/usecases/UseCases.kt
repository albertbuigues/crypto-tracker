package com.buiguesortola.cryptotracker.domain.usecases

import com.buiguesortola.cryptotracker.domain.repository.CryptosRepository

/**
 * Retrieves the top ten best coins from the repository.
 *
 */
class GetTopTenBestCoinsUseCase (private val repository: CryptosRepository) {
    suspend operator fun invoke() = repository.getCoins().map { coins ->
        coins.sortedByDescending { coin -> coin.changePercent }.take(10)
    }
}

/**
 * Retrieves the top ten worst coins from the repository.
 *
 */
class GetTopTenWorstCoinsUseCase (private val repository: CryptosRepository) {
    suspend operator fun invoke() = repository.getCoins().map { coins ->
        coins.sortedBy{ coin -> coin.changePercent }.take(10)
    }
}