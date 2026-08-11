package com.buiguesortola.cryptotracker.domain.repository

import com.buiguesortola.cryptotracker.domain.models.Coin

interface CryptosRepository {
    suspend fun getCoins(): Result<List<Coin>>
}