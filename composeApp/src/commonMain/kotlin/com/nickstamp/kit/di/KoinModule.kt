package com.nickstamp.kit.di

import com.nickstamp.kit.core.network.ApiService
import com.nickstamp.kit.core.storage.DatastoreManager
import com.nickstamp.kit.core.storage.DatastoreManagerImpl
import com.nickstamp.kit.core.storage.example.UserPreferences
import com.nickstamp.kit.feature.settings.di.settingsModule
import com.nickstamp.kit.feature.showcase.di.showcaseModule
import com.nickstamp.kit.network.httpClient
import com.nickstamp.kit.shared.utils.DefaultEffectHandler
import com.nickstamp.kit.shared.utils.EffectHandler
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        modules(
            appModule,
            networkModule,
            storageModule,
            settingsModule,
            showcaseModule
        )
    }
}

val appModule = module {
    single { httpClient() }
    single<EffectHandler> { DefaultEffectHandler() }
}

val networkModule = module {
    single { ApiService(get()) }
}

val storageModule = module {
    // Note: DataStore setup should be done in platform-specific code
    // For now, DatastoreManager will be injected when DataStore is available
    // single<DatastoreManager> { DatastoreManagerImpl(get()) }
    // single { UserPreferences(get()) }
}