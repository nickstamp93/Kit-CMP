package com.nickstamp.kit.feature.intro.presentation

import androidx.compose.runtime.Immutable
import com.nickstamp.kit.feature.intro.domain.model.IntroPage
import com.nickstamp.kit.ui.utils.ToastInfo


interface IntroContract {

    sealed interface Event {
        data object OnNextPageButtonClick : Event
        data object OnPreviousPageButtonClick : Event
        data object OnAcceptTermsClick : Event
        data class OnPageChanged(val pos: Int) : Event
    }

    sealed interface Effect {
        data object GoToHome : Effect
        data class ShowToast(val toastInfo: ToastInfo) : Effect
    }

    @Immutable
    data class State(
        val totalPages: Int = IntroPage.pagesCount(),
        val currentPagePos: Int = 0,
        val htmlText: String = ""
    ) {
        val currentPage = IntroPage.get(currentPagePos)
        val isLastInfoScreen: Boolean = (currentPagePos == totalPages - 2)
        val canGoToNextPage: Boolean = currentPagePos < totalPages - 1
        val canGoToPreviousPage: Boolean = currentPagePos > 0
    }
}