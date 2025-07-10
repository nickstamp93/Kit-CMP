package com.nickstamp.kit.feature.showcase.di

import com.nickstamp.kit.feature.showcase.presentation.ShowcaseViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val showcaseModule = module {
    viewModel { ShowcaseViewModel() }
}