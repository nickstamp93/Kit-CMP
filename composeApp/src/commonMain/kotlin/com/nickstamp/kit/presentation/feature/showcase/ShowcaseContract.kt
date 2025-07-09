package com.nickstamp.kit.presentation.feature.showcase

interface ShowcaseContract {
    data class State(
        val isLoading: Boolean = false
    )
    
    sealed interface Event {
        data class OnDemoButtonClick(val componentName: String) : Event
    }
    
    sealed interface Effect {
        data class ShowMessage(val message: String) : Effect
    }
}