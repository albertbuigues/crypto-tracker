package com.buiguesortola.cryptotracker

import android.app.Application
import com.buiguesortola.cryptotracker.di.androidAppModule
import com.buiguesortola.cryptotracker.di.appModule
import com.buiguesortola.cryptotracker.di.networkModule
import com.buiguesortola.cryptotracker.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CryptoTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CryptoTrackerApplication)
            modules(domainModule, networkModule, androidAppModule, appModule)
        }
    }
}
