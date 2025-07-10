package com.nickstamp.kit.di

import com.nickstamp.kit.core.helpers.AndroidSystemHelper
import com.nickstamp.kit.core.helpers.SystemHelper
import com.nickstamp.kit.ui.utils.AndroidEffectHandler
import com.nickstamp.kit.ui.utils.EffectHandler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single<EffectHandler> { AndroidEffectHandler(get()) }
    single<SystemHelper> { AndroidSystemHelper(androidContext()) }
}