package com.nickstamp.kit.feature.applauncher.di

import com.nickstamp.kit.feature.applauncher.helper.AppUpdateHelper
import com.nickstamp.kit.feature.applauncher.presentation.AppLauncherViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appLauncherModule = module {
    single { AppUpdateHelper(get()) }
    viewModelOf(::AppLauncherViewModel)
}