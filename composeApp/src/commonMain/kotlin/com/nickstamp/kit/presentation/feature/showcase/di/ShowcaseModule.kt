package com.nickstamp.kit.presentation.feature.showcase.di

import com.nickstamp.kit.presentation.feature.showcase.ShowcaseViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val showcaseModule = module {
    viewModel { ShowcaseViewModel() }
}