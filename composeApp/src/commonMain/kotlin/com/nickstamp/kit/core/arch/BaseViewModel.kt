package com.nickstamp.kit.core.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<EVENT, EFFECT, STATE>(initialState: STATE) : ViewModel() {

    private val _effect = Channel<EFFECT>()
    val effect = _effect.receiveAsFlow()

    private val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    abstract fun onEvent(event: EVENT)

    protected fun launchInViewModelScope(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(block = block)

    protected fun setState(reduce: STATE.() -> STATE) = launchInViewModelScope {
        val newState = _state.value.reduce()
        _state.value = newState
    }

    protected fun setEffect(effect: EFFECT) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }
}