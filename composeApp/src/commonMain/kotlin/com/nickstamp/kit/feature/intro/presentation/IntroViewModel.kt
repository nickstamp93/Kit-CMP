package com.nickstamp.kit.feature.intro.presentation

import com.nickstamp.kit.core.arch.BaseViewModel
import com.nickstamp.kit.feature.config.domain.AppConfigDefaults
import com.nickstamp.kit.feature.config.domain.usecase.GetConfigurationUseCase
import com.nickstamp.kit.feature.intro.domain.model.IntroPage
import com.nickstamp.kit.feature.intro.domain.usecase.SetIntroSeenUseCase
import com.nickstamp.kit.feature.intro.presentation.IntroContract.Effect
import com.nickstamp.kit.feature.intro.presentation.IntroContract.Event
import com.nickstamp.kit.feature.intro.presentation.IntroContract.State
import com.nickstamp.kit.ui.utils.ToastInfo

class IntroViewModel(
    private val getConfigurationUseCase: GetConfigurationUseCase,
    private val setIntroSeen: SetIntroSeenUseCase,
) : BaseViewModel<Event, Effect, State>(
    initialState = State()
) {

    init {
        updateTerms()
    }

    override fun onEvent(event: Event) {
        when (event) {
            is Event.OnNextPageButtonClick -> goToNextPage()
            is Event.OnPreviousPageButtonClick -> goToPreviousPage()
            is Event.OnPageChanged -> handleOnPageChanged(event.pos)
            is Event.OnAcceptTermsClick -> completeIntroAndRedirect()
        }
    }

    private fun handleOnPageChanged(pos: Int) {
        setState { copy(currentPagePos = pos) }
    }

    private fun goToPreviousPage() {
        if (state.value.canGoToPreviousPage) {
            val previousPage = (state.value.currentPagePos - 1).coerceAtLeast(0)
            setState { copy(currentPagePos = previousPage) }
        }
    }

    private fun goToNextPage() {
        if (state.value.canGoToNextPage) {
            val nextPage = (state.value.currentPagePos + 1).coerceAtMost(state.value.totalPages - 1)
            setState { copy(currentPagePos = nextPage) }
        } else {
            completeIntroAndRedirect()
        }
    }

    private fun completeIntroAndRedirect() = launchInViewModelScope {
        setIntroSeen(true)
            .onSuccess { goToHome() }
            .onFailure { setEffect(Effect.ShowToast(ToastInfo(message = it.message ?: "Unknown error"))) }
    }

    private fun goToHome() {
        setEffect(Effect.GoToHome)
    }

    private fun updateTerms() = launchInViewModelScope {
        try {
            val introConfig = getConfigurationUseCase().appIntroConfig
            val termsText = introConfig.termsText.ifEmpty { AppConfigDefaults.DEFAULT_TERMS_TEXT }
            setState {
                copy(
                    htmlText = termsText
                )
            }
        } catch (e: Exception) {
            // Use default terms if config fails
            setState {
                copy(
                    htmlText = AppConfigDefaults.DEFAULT_TERMS_TEXT
                )
            }
        }
    }

}