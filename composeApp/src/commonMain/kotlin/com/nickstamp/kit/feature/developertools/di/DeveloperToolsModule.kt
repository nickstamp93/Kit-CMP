package com.nickstamp.kit.feature.developertools.di

import com.nickstamp.kit.feature.developertools.presentation.DeveloperToolsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val developerToolsModule = module {
    viewModel { DeveloperToolsViewModel(get(), get()) }
}