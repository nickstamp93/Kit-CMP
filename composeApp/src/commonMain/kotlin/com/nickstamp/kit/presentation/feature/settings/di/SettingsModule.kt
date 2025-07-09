package com.nickstamp.kit.presentation.feature.settings.di

import com.nickstamp.kit.presentation.feature.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    viewModel { SettingsViewModel() }
}