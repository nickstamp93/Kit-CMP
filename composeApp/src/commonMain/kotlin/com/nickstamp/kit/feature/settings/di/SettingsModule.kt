package com.nickstamp.kit.feature.settings.di

import com.nickstamp.kit.feature.settings.domain.usecase.GetAppThemeUseCase
import com.nickstamp.kit.feature.settings.domain.usecase.SetAppThemeUseCase
import com.nickstamp.kit.feature.settings.presentation.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    single { GetAppThemeUseCase(get()) }
    single { SetAppThemeUseCase(get()) }
    viewModel { SettingsViewModel(get(), get(), get()) }
}