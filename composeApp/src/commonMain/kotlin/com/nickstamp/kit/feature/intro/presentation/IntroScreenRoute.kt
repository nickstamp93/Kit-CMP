package com.nickstamp.kit.feature.intro.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nickstamp.kit.ui.theme.AppTheme
import com.nickstamp.kit.ui.utils.EffectHandler
import com.nickstamp.kit.ui.utils.ToastInfo
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun IntroScreenRoute(
    onNavigateToHome: () -> Unit,
    effectHandler: EffectHandler,
    viewModel: IntroViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is IntroContract.Effect.GoToHome -> onNavigateToHome()
                is IntroContract.Effect.ShowToast -> effectHandler.showToast(
                    ToastInfo(effect.toastInfo.message)
                )
            }
        }
    }


    AppTheme(useDarkTheme = false) {
        IntroScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}