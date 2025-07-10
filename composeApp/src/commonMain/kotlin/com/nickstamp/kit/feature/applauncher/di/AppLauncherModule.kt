package com.nickstamp.kit.feature.applauncher.di

import com.nickstamp.kit.feature.applauncher.helper.AppUpdateHelper
import com.nickstamp.kit.feature.applauncher.presentation.AppLauncherViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appLauncherModule = module {
    single { AppUpdateHelper(get()) }
    viewModel { AppLauncherViewModel(get(), get(), get()) }
}