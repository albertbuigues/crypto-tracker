package com.buiguesortola.cryptotracker.domain

sealed class CryptoError: Exception() {
    class EuroRateNotFoundError(override val message: String): CryptoError()
    class NetworkError(override val message: String): CryptoError()
}