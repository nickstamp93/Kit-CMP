package com.nickstamp.kit.feature.showcase.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nickstamp.kit.shared.utils.EffectHandler
import com.nickstamp.kit.shared.utils.ToastInfo
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
            }
        }
    }

    ShowcaseScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onNavigateBack = onNavigateBack
    )
}