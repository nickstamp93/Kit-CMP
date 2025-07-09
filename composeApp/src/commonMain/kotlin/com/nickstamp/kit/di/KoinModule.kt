package com.nickstamp.kit.di

import com.nickstamp.kit.ui.utils.DefaultEffectHandler
import com.nickstamp.kit.ui.utils.EffectHandler
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::DefaultEffectHandler) bind EffectHandler::class
}