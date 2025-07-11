package com.nickstamp.kit.feature.applauncher.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nickstamp.kit.ui.theme.AppTheme
import com.nickstamp.kit.ui.utils.EffectHandler
import com.nickstamp.kit.ui.utils.ToastInfo
import com.nickstamp.kit.ui.utils.ToastDuration
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppLauncherScreenRoute(
    onNavigateToHome: () -> Unit,
    onNavigateToIntro: () -> Unit,
    effectHandler: EffectHandler,
    viewModel: AppLauncherViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is AppLauncherContract.Effect.GoToHome -> onNavigateToHome()
                is AppLauncherContract.Effect.GoToAppIntro -> onNavigateToIntro()
                is AppLauncherContract.Effect.OpenWebUrl -> {
                    try {
                        uriHandler.openUri(effect.url)
                    } catch (_: Exception) {
                        // Fallback: show error toast
                        effectHandler.showToast(
                            ToastInfo("Could not open URL", ToastDuration.SHORT)
                        )
                    }
                }
                is AppLauncherContract.Effect.CloseApp -> effectHandler.closeApp()
            }
        }
    }

    AppTheme(useDarkTheme = false) {
        AppLaunchScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}