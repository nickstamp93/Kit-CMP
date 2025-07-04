package com.nickstamp.kit.feature.settings.presentation

import com.nickstamp.kit.core.presentation.BaseViewModel

class SettingsViewModel : BaseViewModel<SettingsContract.Event, SettingsContract.Effect, SettingsContract.State>(
    initialState = SettingsContract.State()
) {
    
    override fun onEvent(event: SettingsContract.Event) {
        when (event) {
            is SettingsContract.Event.ToggleTheme -> toggleTheme()
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
}