package com.nickstamp.kit.di

import com.nickstamp.kit.network.httpClient
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}

val appModule = module {
    single { httpClient() }
}