package com.buiguesortola.cryptotracker.domain.di

import com.buiguesortola.cryptotracker.domain.usecases.GetTopTenBestCoinsUseCase
import com.buiguesortola.cryptotracker.domain.usecases.GetTopTenWorstCoinsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetTopTenBestCoinsUseCase(get()) }
    factory { GetTopTenWorstCoinsUseCase(get()) }
}