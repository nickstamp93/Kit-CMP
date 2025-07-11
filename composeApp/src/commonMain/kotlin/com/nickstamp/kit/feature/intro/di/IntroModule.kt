package com.nickstamp.kit.feature.intro.di

import com.nickstamp.kit.feature.intro.domain.usecase.IsIntroSeenUseCase
import com.nickstamp.kit.feature.intro.domain.usecase.SetIntroSeenUseCase
import com.nickstamp.kit.feature.intro.presentation.IntroViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val introModule = module {
    single { SetIntroSeenUseCase(get()) }
    single { IsIntroSeenUseCase(get()) }
    viewModelOf(::IntroViewModel)
}