package com.buiguesortola.cryptotracker

import com.buiguesortola.cryptotracker.di.appModule
import com.buiguesortola.cryptotracker.di.networkModule
import com.buiguesortola.cryptotracker.domain.di.domainModule
import com.buiguesortola.cryptotracker.ui.viewmodels.CryptoCoinsListViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(appModule, domainModule, networkModule)
    }
}

class KoinHelper : KoinComponent {
    fun getCryptoCoinsListViewModel(): CryptoCoinsListViewModel = get()
}
