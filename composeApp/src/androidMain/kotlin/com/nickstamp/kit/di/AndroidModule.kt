package com.nickstamp.kit.di

import com.nickstamp.kit.core.helpers.AndroidSystemHelper
import com.nickstamp.kit.core.helpers.SystemHelper
import com.nickstamp.kit.ui.utils.AndroidEffectHandler
import com.nickstamp.kit.ui.utils.EffectHandler
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val androidModule = module {
    singleOf(::AndroidEffectHandler) bind EffectHandler::class
    single<SystemHelper> { AndroidSystemHelper(androidContext()) }
}