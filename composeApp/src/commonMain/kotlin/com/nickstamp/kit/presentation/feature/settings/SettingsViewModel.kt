package com.nickstamp.kit.presentation.feature.settings

import com.nickstamp.kit.core.arch.BaseViewModel

class SettingsViewModel : BaseViewModel<SettingsContract.Event, SettingsContract.Effect, SettingsContract.State>(
    initialState = SettingsContract.State()
) {
    
    override fun onEvent(event: SettingsContract.Event) {
        when (event) {
            is SettingsContract.Event.ToggleTheme -> toggleTheme()
            is SettingsContract.Event.NavigateToShowcase -> navigateToShowcase()
        }
    }
    
    private fun toggleTheme() {
        setState { 
            copy(isDarkTheme = !isDarkTheme)
        }
        setEffect(SettingsContract.Effect.ShowMessage("Theme switched"))
    }
    
    fun updateTheme(isDarkTheme: Boolean) {
        setState { 
            copy(isDarkTheme = isDarkTheme)
        }
    }
    
    private fun navigateToShowcase() {
        setEffect(SettingsContract.Effect.NavigateToShowcase)
    }
}