package com.nickstamp.kit.feature.home.di

import com.nickstamp.kit.feature.home.presentation.HomeViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::HomeViewModel)
}