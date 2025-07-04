package com.nickstamp.kit.feature.home.presentation

interface HomeContract {
    data class State(
        val isLoading: Boolean = false,
        val showContent: Boolean = false,
        val greeting: String = "",
        val error: String? = null
    )
    
    sealed interface Event {
        data object ToggleContent : Event
        data object LoadGreeting : Event
    }
    
    sealed interface Effect {
        data class ShowError(val message: String) : Effect
    }
}