package com.nickstamp.kit.feature.home.di

import com.nickstamp.kit.feature.home.domain.usecase.GetGreetingUseCase
import com.nickstamp.kit.feature.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    single { GetGreetingUseCase() }
    viewModel { HomeViewModel(get()) }
}