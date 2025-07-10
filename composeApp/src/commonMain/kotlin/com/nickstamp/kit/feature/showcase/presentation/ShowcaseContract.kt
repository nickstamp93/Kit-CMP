package com.nickstamp.kit.feature.showcase.presentation

import com.nickstamp.kit.feature.config.domain.model.Configuration

interface ShowcaseContract {
    data class State(
        val isLoading: Boolean = false,
        val configuration: Configuration? = null,
        val isConfigLoading: Boolean = false,
        val configError: String? = null
    )
    
    sealed interface Event {
        data object OnBack : Event
        data class OnDemoButtonClick(val componentName: String) : Event
    }
    
    sealed interface Effect {
        data object NavigateBack : Effect
        data class ShowMessage(val message: String) : Effect
        data class ShowConfigurationFetched(val configuration: Configuration) : Effect
        data class ShowConfigurationError(val error: String) : Effect
    }
}