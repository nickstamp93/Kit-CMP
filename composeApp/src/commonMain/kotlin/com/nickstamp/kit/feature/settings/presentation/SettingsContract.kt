package com.nickstamp.kit.feature.settings.presentation

interface SettingsContract {
    data class State(
        val isDarkTheme: Boolean = false,
        val isLoading: Boolean = false,
        val appVersion: String = "1.0"
    )
    
    sealed interface Event {
        data object ToggleTheme : Event
        data object NavigateToDeveloperTools : Event
    }
    
    sealed interface Effect {
        data class ShowMessage(val message: String) : Effect
        data object NavigateToDeveloperTools : Effect
    }
}