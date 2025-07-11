package com.nickstamp.kit.feature.home.presentation

interface HomeContract {
    
    data class State(
        val isLoading: Boolean = false,
        val appName: String = "",
        val error: String? = null
    )
    
    sealed interface Event {
        data object LoadHomeData : Event
        data object NavigateToSettings : Event
        data object RefreshData : Event
    }
    
    sealed interface Effect {
        data object NavigateToSettings : Effect
        data class ShowError(val message: String) : Effect
        data class ShowToast(val message: String) : Effect
    }
}