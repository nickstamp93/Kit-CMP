package com.nickstamp.kit.di

import com.nickstamp.kit.core.helpers.IosSystemHelper
import com.nickstamp.kit.core.helpers.SystemHelper
import com.nickstamp.kit.ui.utils.IosEffectHandler
import com.nickstamp.kit.ui.utils.EffectHandler
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val iosModule = module {
    singleOf(::IosEffectHandler) bind EffectHandler::class
    singleOf(::IosSystemHelper) bind SystemHelper::class
}