package com.nickstamp.kit.di

import com.nickstamp.kit.feature.home.di.homeModule
import com.nickstamp.kit.network.httpClient
import com.nickstamp.kit.shared.utils.DefaultEffectHandler
import com.nickstamp.kit.shared.utils.EffectHandler
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        modules(
            appModule,
            homeModule
        )
    }
}

val appModule = module {
    single { httpClient() }
    single<EffectHandler> { DefaultEffectHandler() }
}