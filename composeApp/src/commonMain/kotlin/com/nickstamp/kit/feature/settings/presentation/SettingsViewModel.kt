package com.nickstamp.kit.feature.settings.presentation

import com.nickstamp.kit.core.arch.BaseViewModel
import com.nickstamp.kit.core.helpers.SystemHelper
import com.nickstamp.kit.feature.config.domain.usecase.GetConfigurationUseCase
import com.nickstamp.kit.feature.settings.domain.usecase.GetAppThemeUseCase
import com.nickstamp.kit.feature.settings.domain.usecase.SetAppThemeUseCase

class SettingsViewModel(
    private val getAppThemeUseCase: GetAppThemeUseCase,
    private val setAppThemeUseCase: SetAppThemeUseCase,
    private val systemHelper: SystemHelper,
    private val getConfigurationUseCase: GetConfigurationUseCase
) : BaseViewModel<SettingsContract.Event, SettingsContract.Effect, SettingsContract.State>(
    initialState = SettingsContract.State()
) {

    init {
        loadInitialData()
    }

    override fun onEvent(event: SettingsContract.Event) {
        when (event) {
            is SettingsContract.Event.ToggleTheme -> toggleTheme()
            is SettingsContract.Event.NavigateToDeveloperTools -> navigateToDeveloperTools()
        }
    }

    private fun loadInitialData() {
        launchInViewModelScope {
            val savedTheme = getAppThemeUseCase()
            val appVersion = systemHelper.getCurrentVersionName()
            setState {
                copy(
                    isDarkTheme = savedTheme,
                    appVersion = appVersion
                )
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

    private fun navigateToDeveloperTools() {
        launchInViewModelScope { getConfigurationUseCase(forceFetch = true) }
        setEffect(SettingsContract.Effect.NavigateToDeveloperTools)
    }
}