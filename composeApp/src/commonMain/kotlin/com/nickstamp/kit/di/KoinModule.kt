package com.nickstamp.kit.di

import com.nickstamp.kit.core.network.ApiService
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