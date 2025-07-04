package com.nickstamp.kit.feature.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nickstamp.kit.shared.utils.EffectHandler
import com.nickstamp.kit.shared.utils.ToastInfo
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreenRoute(
    effectHandler: EffectHandler,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeContract.Effect.ShowError -> {
                    effectHandler.showToast(ToastInfo(effect.message))
                }
            }
        }
    }

    HomeScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}