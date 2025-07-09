package com.nickstamp.kit.feature.showcase.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nickstamp.kit.ui.utils.EffectHandler
import com.nickstamp.kit.ui.utils.ToastInfo
import org.koin.compose.koinInject

@Composable
fun ShowcaseScreenRoute(
    onNavigateBack: () -> Unit,
    effectHandler: EffectHandler,
    viewModel: ShowcaseViewModel = koinInject()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is ShowcaseContract.Effect.ShowMessage -> {
                    effectHandler.showToast(ToastInfo(effect.message))
                }

                is ShowcaseContract.Effect.ShowConfigurationError -> {
                    effectHandler.showToast(ToastInfo(effect.error))
                }
                is ShowcaseContract.Effect.ShowConfigurationFetched -> {
                    effectHandler.showToast(ToastInfo(effect.configuration.toString()))
                }
            }
        }
    }

    ShowcaseScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onNavigateBack = onNavigateBack
    )
}