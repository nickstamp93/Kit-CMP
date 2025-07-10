package com.nickstamp.kit.feature.showcase.presentation

import com.nickstamp.kit.core.arch.BaseViewModel
import com.nickstamp.kit.feature.config.domain.usecase.GetConfigurationUseCase

class ShowcaseViewModel() : BaseViewModel<ShowcaseContract.Event, ShowcaseContract.Effect, ShowcaseContract.State>(
    initialState = ShowcaseContract.State()
) {
    
    override fun onEvent(event: ShowcaseContract.Event) {
        when (event) {
            is ShowcaseContract.Event.OnBack -> navigateBack()
            is ShowcaseContract.Event.OnDemoButtonClick -> onDemoButtonClick(event.componentName)
        }
    }
    
    private fun navigateBack() {
        setEffect(ShowcaseContract.Effect.NavigateBack)
    }
    
    private fun onDemoButtonClick(componentName: String) {
        setEffect(ShowcaseContract.Effect.ShowMessage(componentName))
    }

}