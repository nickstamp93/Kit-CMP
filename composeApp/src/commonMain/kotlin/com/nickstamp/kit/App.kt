package com.nickstamp.kit

import androidx.compose.foundation.background
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.nickstamp.kit.di.KoinConfig
import com.nickstamp.kit.feature.settings.domain.usecase.GetAppThemeUseCase
import com.nickstamp.kit.navigation.AppNavigation
import com.nickstamp.kit.ui.theme.AppTheme
import com.nickstamp.kit.ui.theme.AppTheme.colors
import com.nickstamp.kit.ui.utils.EffectHandler
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    KoinConfig {
        AppContent()
    }
}

@Composable
private fun AppContent() {
    val getAppThemeUseCase: GetAppThemeUseCase = koinInject()

    // Load theme from preferences directly
    val isDarkTheme by getAppThemeUseCase.getThemeFlow().collectAsState(initial = false)

    AppTheme(useDarkTheme = isDarkTheme) {
        val navController = rememberNavController()
        val effectHandler: EffectHandler = koinInject()

        Scaffold(
            modifier = Modifier.background(colors.background)
        ) {
            AppNavigation(
                navController = navController,
                effectHandler = effectHandler,
            )
        }
    }
}