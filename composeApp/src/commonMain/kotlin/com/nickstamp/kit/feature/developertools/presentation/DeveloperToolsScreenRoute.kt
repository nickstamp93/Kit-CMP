package com.nickstamp.kit.feature.developertools.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nickstamp.kit.ui.utils.EffectHandler
import com.nickstamp.kit.ui.utils.ToastInfo
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DeveloperToolsScreenRoute(
    onNavigateBack: () -> Unit,
    onNavigateToShowcase: () -> Unit,
    onNavigateToIntro: () -> Unit,
    effectHandler: EffectHandler,
    viewModel: DeveloperToolsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is DeveloperToolsContract.Effect.NavigateBack -> {
                    onNavigateBack()
                }
                is DeveloperToolsContract.Effect.NavigateToShowcase -> {
                    onNavigateToShowcase()
                }
                is DeveloperToolsContract.Effect.NavigateToIntro -> {
                    onNavigateToIntro()
                }
                is DeveloperToolsContract.Effect.ShowToast -> {
                    effectHandler.showToast(ToastInfo(effect.message))
                }
            }
        }
    }

    DeveloperToolsScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}