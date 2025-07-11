package com.nickstamp.kit.feature.home.presentation

import androidx.lifecycle.viewModelScope
import com.nickstamp.kit.core.arch.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel<HomeContract.Event, HomeContract.Effect, HomeContract.State>(
    initialState = HomeContract.State()
) {
    
    init {
        loadHomeData()
    }
    
    override fun onEvent(event: HomeContract.Event) {
        when (event) {
            HomeContract.Event.LoadHomeData -> loadHomeData()
            HomeContract.Event.NavigateToSettings -> navigateToSettings()
            HomeContract.Event.RefreshData -> refreshData()
        }
    }
    
    private fun loadHomeData() {
        viewModelScope.launch {
            setState { copy(isLoading = true, error = null) }

            delay(3000)

            try {
                // TODO: Load actual home data when implementing specific features
                // For now, just set app name and loading state
                setState { 
                    copy(
                        isLoading = false,
                        appName = "Kit CMP",
                        error = null
                    )
                }
            } catch (e: Exception) {
                setState { 
                    copy(
                        isLoading = false,
                        error = e.message
                    )
                }
                setEffect(HomeContract.Effect.ShowError("Failed to load home data: ${e.message}"))
            }
        }
    }
    
    private fun navigateToSettings() {
        setEffect(HomeContract.Effect.NavigateToSettings)
    }
    
    private fun refreshData() {
        setEffect(HomeContract.Effect.ShowToast("Refreshing data..."))
        loadHomeData()
    }
}