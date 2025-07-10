package com.nickstamp.kit.feature.applauncher.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nickstamp.kit.ui.utils.EffectHandler
import org.koin.compose.viewmodel.koinViewModel
import kotlin.system.exitProcess

@Composable
fun AppLauncherScreenRoute(
    onNavigateToHome: () -> Unit,
    onNavigateToIntro: () -> Unit,
    onOpenWebUrl: (String) -> Unit,
    effectHandler: EffectHandler,
    viewModel: AppLauncherViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is AppLauncherContract.Effect.GoToHome -> onNavigateToHome()
                is AppLauncherContract.Effect.GoToAppIntro -> onNavigateToIntro()
                is AppLauncherContract.Effect.OpenWebUrl -> onOpenWebUrl(effect.url)
                is AppLauncherContract.Effect.CloseApp -> {
                    // Platform-specific implementation would be better
                    exitProcess(0)
                }
            }
        }
    }

    AppLaunchScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}