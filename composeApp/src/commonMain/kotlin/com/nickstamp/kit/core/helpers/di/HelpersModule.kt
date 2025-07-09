package com.nickstamp.kit.core.helpers.di

import com.nickstamp.kit.core.helpers.DateTimeHelper
import com.nickstamp.kit.core.helpers.impl.DateTimeHelperImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val helpersModule = module {
    singleOf(::DateTimeHelperImpl) bind DateTimeHelper::class
}