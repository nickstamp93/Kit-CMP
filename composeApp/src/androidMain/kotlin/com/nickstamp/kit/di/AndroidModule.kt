package com.nickstamp.kit.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single { androidContext() }
}