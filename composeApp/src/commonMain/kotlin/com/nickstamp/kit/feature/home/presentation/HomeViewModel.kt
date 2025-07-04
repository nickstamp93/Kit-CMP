package com.nickstamp.kit.feature.home.presentation

import com.nickstamp.kit.core.presentation.BaseViewModel
import com.nickstamp.kit.feature.home.domain.usecase.GetGreetingUseCase

class HomeViewModel(
    private val getGreetingUseCase: GetGreetingUseCase
) : BaseViewModel<HomeContract.Event, HomeContract.Effect, HomeContract.State>(
    initialState = HomeContract.State()
) {
    
    override fun onEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.ToggleContent -> toggleContent()
            is HomeContract.Event.LoadGreeting -> loadGreeting()
        }
    }
    
    private fun toggleContent() {
        setState { 
            copy(showContent = !showContent)
        }
        if (!state.value.showContent && state.value.greeting.isEmpty()) {
            loadGreeting()
        }
    }
    
    private fun loadGreeting() {
        launchInViewModelScope {
            try {
                setState { copy(isLoading = true, error = null) }
                val greeting = getGreetingUseCase.invoke()
                setState { 
                    copy(
                        isLoading = false,
                        greeting = greeting,
                        showContent = true
                    )
                }
            } catch (e: Exception) {
                setState { copy(isLoading = false) }
                setEffect(HomeContract.Effect.ShowError("Failed to load greeting: ${e.message}"))
            }
        }
    }
}