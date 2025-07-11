package com.nickstamp.kit.feature.showcase.di

import com.nickstamp.kit.feature.showcase.presentation.ShowcaseViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val showcaseModule = module {
    viewModelOf(::ShowcaseViewModel)
}