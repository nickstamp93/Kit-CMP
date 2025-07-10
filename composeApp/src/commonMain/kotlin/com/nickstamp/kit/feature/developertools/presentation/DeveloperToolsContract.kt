package com.nickstamp.kit.feature.developertools.presentation

interface DeveloperToolsContract {
    data class State(
        val isLoading: Boolean = false,
        val showConfigurationSheet: Boolean = false,
        val configuration: String = ""
    )
    
    sealed interface Event {
        data object OnBack : Event
        data object NavigateToShowcase : Event
        data object NavigateToIntro : Event
        data object ClearAllPreferences : Event
        data object ShowConfiguration : Event
        data object HideConfiguration : Event
    }
    
    sealed interface Effect {
        data object NavigateBack : Effect
        data object NavigateToShowcase : Effect
        data object NavigateToIntro : Effect
        data class ShowToast(val message: String) : Effect
    }
}