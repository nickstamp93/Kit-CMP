package com.nickstamp.kit.feature.settings.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nickstamp.kit.ui.utils.EffectHandler
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsScreenRoute(
    onNavigateBack: () -> Unit,
    effectHandler: EffectHandler,
    onNavigateToDeveloperTools: () -> Unit,
    viewModel: SettingsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SettingsContract.Effect.ShowMessage -> {
                    // Handle showing message if needed - could show toast or snackbar
                }

                is SettingsContract.Effect.NavigateToDeveloperTools -> {
                    onNavigateToDeveloperTools()
                }
            }
        }
    }

    SettingsScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onNavigateBack = onNavigateBack
    )
}