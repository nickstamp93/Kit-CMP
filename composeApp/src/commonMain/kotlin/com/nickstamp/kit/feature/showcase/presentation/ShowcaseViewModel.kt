package com.nickstamp.kit.feature.showcase.presentation

import com.nickstamp.kit.core.arch.BaseViewModel
import com.nickstamp.kit.feature.config.domain.usecase.configuration.GetConfigurationUseCase
import com.nickstamp.kit.feature.config.domain.usecase.configuration.RefreshConfigurationUseCase

class ShowcaseViewModel(
    private val getConfigurationUseCase: GetConfigurationUseCase,
    private val refreshConfigurationUseCase: RefreshConfigurationUseCase
) : BaseViewModel<ShowcaseContract.Event, ShowcaseContract.Effect, ShowcaseContract.State>(
    initialState = ShowcaseContract.State()
) {
    
    override fun onEvent(event: ShowcaseContract.Event) {
        when (event) {
            is ShowcaseContract.Event.OnDemoButtonClick -> onRefreshConfigurationClick()
            is ShowcaseContract.Event.OnFetchConfigurationClick -> onFetchConfigurationClick()
            is ShowcaseContract.Event.OnRefreshConfigurationClick -> onRefreshConfigurationClick()
        }
    }
    
    private fun onDemoButtonClick(componentName: String) {
        setEffect(ShowcaseContract.Effect.ShowMessage("Demo button clicked: $componentName"))
    }
    
    private fun onFetchConfigurationClick() {
        launchInViewModelScope {
            setState { copy(isConfigLoading = true, configError = null) }
            
            try {
                val configuration = getConfigurationUseCase()
                setState { 
                    copy(
                        isConfigLoading = false,
                        configuration = configuration,
                        configError = null
                    )
                }
                setEffect(ShowcaseContract.Effect.ShowConfigurationFetched(configuration))
            } catch (e: Exception) {
                val errorMessage = e.message ?: "Failed to fetch configuration"
                setState { 
                    copy(
                        isConfigLoading = false,
                        configError = errorMessage
                    )
                }
                setEffect(ShowcaseContract.Effect.ShowConfigurationError(errorMessage))
            }
        }
    }
    
    private fun onRefreshConfigurationClick() {
        launchInViewModelScope {
            setState { copy(isConfigLoading = true, configError = null) }
            
            try {
                val configuration = refreshConfigurationUseCase()
                setState { 
                    copy(
                        isConfigLoading = false,
                        configuration = configuration,
                        configError = null
                    )
                }
                setEffect(ShowcaseContract.Effect.ShowConfigurationFetched(configuration))
            } catch (e: Exception) {
                val errorMessage = e.message ?: "Failed to refresh configuration"
                setState { 
                    copy(
                        isConfigLoading = false,
                        configError = errorMessage
                    )
                }
                setEffect(ShowcaseContract.Effect.ShowConfigurationError(errorMessage))
            }
        }
    }
}