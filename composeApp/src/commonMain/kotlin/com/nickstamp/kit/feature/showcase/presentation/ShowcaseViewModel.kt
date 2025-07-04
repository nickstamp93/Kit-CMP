package com.nickstamp.kit.feature.showcase.presentation

import com.nickstamp.kit.core.presentation.BaseViewModel

class ShowcaseViewModel : BaseViewModel<ShowcaseContract.Event, ShowcaseContract.Effect, ShowcaseContract.State>(
    initialState = ShowcaseContract.State()
) {
    
    override fun onEvent(event: ShowcaseContract.Event) {
        when (event) {
            is ShowcaseContract.Event.OnDemoButtonClick -> onDemoButtonClick(event.componentName)
        }
    }
    
    private fun onDemoButtonClick(componentName: String) {
        setEffect(ShowcaseContract.Effect.ShowMessage("Clicked: $componentName"))
    }
}