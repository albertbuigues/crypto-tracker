package com.bitpanda.livechallenge.domain.repository

import com.bitpanda.livechallenge.domain.models.Coin

interface CryptosRepository {
    suspend fun getCoins(): Result<List<Coin>>
}