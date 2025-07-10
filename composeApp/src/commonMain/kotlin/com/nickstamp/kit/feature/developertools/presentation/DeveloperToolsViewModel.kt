package com.nickstamp.kit.feature.developertools.presentation

import com.nickstamp.kit.core.arch.BaseViewModel
import com.nickstamp.kit.core.storage.DatastoreManager
import com.nickstamp.kit.feature.config.domain.Configurator
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DeveloperToolsViewModel(
    private val datastoreManager: DatastoreManager,
    private val configurator: Configurator
) : BaseViewModel<DeveloperToolsContract.Event, DeveloperToolsContract.Effect, DeveloperToolsContract.State>(
    initialState = DeveloperToolsContract.State()
) {
    
    override fun onEvent(event: DeveloperToolsContract.Event) {
        when (event) {
            is DeveloperToolsContract.Event.NavigateToShowcase -> navigateToShowcase()
            is DeveloperToolsContract.Event.NavigateToIntro -> navigateToIntro()
            is DeveloperToolsContract.Event.ClearAllPreferences -> clearAllPreferences()
            is DeveloperToolsContract.Event.ShowConfiguration -> showConfiguration()
            is DeveloperToolsContract.Event.HideConfiguration -> hideConfiguration()
        }
    }
    
    private fun navigateToShowcase() {
        setEffect(DeveloperToolsContract.Effect.NavigateToShowcase)
    }
    
    private fun navigateToIntro() {
        setEffect(DeveloperToolsContract.Effect.NavigateToIntro)
    }
    
    private fun clearAllPreferences() = launchInViewModelScope {
        try {
            datastoreManager.clear()
            setEffect(DeveloperToolsContract.Effect.ShowToast("All preferences cleared"))
        } catch (e: Exception) {
            setEffect(DeveloperToolsContract.Effect.ShowToast("Error clearing preferences: ${e.message}"))
        }
    }
    
    private fun showConfiguration() = launchInViewModelScope {
        try {
            val config = configurator.getConfiguration()
            val json = Json { prettyPrint = true }
            val configJson = json.encodeToString(config)
            setState { 
                copy(
                    showConfigurationSheet = true,
                    configuration = configJson
                )
            }
        } catch (e: Exception) {
            setEffect(DeveloperToolsContract.Effect.ShowToast("Error loading configuration: ${e.message}"))
        }
    }
    
    private fun hideConfiguration() {
        setState { 
            copy(
                showConfigurationSheet = false,
                configuration = ""
            )
        }
    }
}