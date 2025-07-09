package com.nickstamp.kit.di

import com.nickstamp.kit.core.network.ApiService
import com.nickstamp.kit.core.network.httpClient
import com.nickstamp.kit.ui.utils.DefaultEffectHandler
import com.nickstamp.kit.ui.utils.EffectHandler
import org.koin.dsl.module

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