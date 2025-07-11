package com.nickstamp.kit.feature.settings.di

import com.nickstamp.kit.feature.settings.domain.usecase.GetAppThemeUseCase
import com.nickstamp.kit.feature.settings.domain.usecase.SetAppThemeUseCase
import com.nickstamp.kit.feature.settings.presentation.SettingsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val settingsModule = module {
    singleOf(::GetAppThemeUseCase)
    singleOf(::SetAppThemeUseCase)
    viewModelOf(::SettingsViewModel)
}