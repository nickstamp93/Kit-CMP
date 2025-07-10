package com.nickstamp.kit.feature.settings.presentation

import com.nickstamp.kit.core.arch.BaseViewModel
import com.nickstamp.kit.feature.settings.domain.usecase.GetAppThemeUseCase
import com.nickstamp.kit.feature.settings.domain.usecase.SetAppThemeUseCase

class SettingsViewModel(
    private val getAppThemeUseCase: GetAppThemeUseCase,
    private val setAppThemeUseCase: SetAppThemeUseCase
) : BaseViewModel<SettingsContract.Event, SettingsContract.Effect, SettingsContract.State>(
    initialState = SettingsContract.State()
) {

    init {
        loadThemePreference()
    }

    override fun onEvent(event: SettingsContract.Event) {
        when (event) {
            is SettingsContract.Event.ToggleTheme -> toggleTheme()
            is SettingsContract.Event.NavigateToDeveloperTools -> navigateToDeveloperTools()
        }
    }

    private fun loadThemePreference() {
        launchInViewModelScope {
            val savedTheme = getAppThemeUseCase()
            setState {
                copy(isDarkTheme = savedTheme)
            }
        }
    }

    private fun toggleTheme() {
        val newTheme = !state.value.isDarkTheme
        launchInViewModelScope {
            val result = setAppThemeUseCase(newTheme)
            if (result.isSuccess) {
                setState {
                    copy(isDarkTheme = newTheme)
                }
                setEffect(SettingsContract.Effect.ShowMessage("Theme switched"))
            } else {
                setEffect(SettingsContract.Effect.ShowMessage("Failed to change theme"))
            }
        }
    }

    fun updateTheme(isDarkTheme: Boolean) {
        setState {
            copy(isDarkTheme = isDarkTheme)
        }
    }

    private fun navigateToDeveloperTools() {
        setEffect(SettingsContract.Effect.NavigateToDeveloperTools)
    }
}