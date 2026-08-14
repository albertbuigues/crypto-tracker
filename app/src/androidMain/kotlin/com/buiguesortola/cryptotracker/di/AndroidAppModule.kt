package com.buiguesortola.cryptotracker.di

import com.buiguesortola.cryptotracker.ui.viewmodels.CryptoCoinsListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val androidAppModule =
    module {
        viewModel { get<CryptoCoinsListViewModel>() }
    }
