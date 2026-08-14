package com.buiguesortola.cryptotracker.di

import com.buiguesortola.cryptotracker.ui.viewmodels.CryptoCoinsListViewModel
import org.koin.dsl.module

val appModule =
    module {
        factory {
            CryptoCoinsListViewModel(
                get(),
                get(),
            )
        }
    }
