package com.buiguesortola.cryptotracker.di

import com.buiguesortola.cryptotracker.ui.viewmodels.CryptoCoinsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { CryptoCoinsListViewModel(get(), get()) }
}