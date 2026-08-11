package com.buiguesortola.cryptotracker.di

import com.buiguesortola.cryptotracker.api.KtorCryptoApi
import com.buiguesortola.cryptotracker.domain.repository.CryptosRepository
import com.buiguesortola.cryptotracker.repository.CryptoRepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import com.buiguesortola.cryptotracker.network.BuildKonfig

val networkModule = module {
    single {
        HttpClient {
            defaultRequest {
                url("https://rest.coincap.io/v3/")
                header("Authorization", "Bearer ${BuildKonfig.API_TOKEN}")
            }
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true} )
            }
        }
    }
    single { KtorCryptoApi(get()) }
    single<CryptosRepository> { CryptoRepositoryImpl(get()) }
}