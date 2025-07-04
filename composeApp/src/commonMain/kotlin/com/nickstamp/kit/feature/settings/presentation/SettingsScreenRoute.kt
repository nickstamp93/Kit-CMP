package com.nickstamp.kit.feature.settings.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nickstamp.kit.shared.utils.EffectHandler
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsScreenRoute(
    effectHandler: EffectHandler,
    onThemeChange: (Boolean) -> Unit,
    currentTheme: Boolean,
    viewModel: SettingsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    
    // Sync the external theme state with the ViewModel
    LaunchedEffect(currentTheme) {
        viewModel.updateTheme(currentTheme)
    }
    
    // Handle theme changes
    LaunchedEffect(state.isDarkTheme) {
        onThemeChange(state.isDarkTheme)
    }

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SettingsContract.Effect.ShowMessage -> {
                    // Handle showing message if needed
                }
            }
        }
    }

    SettingsScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}