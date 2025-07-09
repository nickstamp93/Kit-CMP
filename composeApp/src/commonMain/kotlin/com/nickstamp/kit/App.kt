package com.nickstamp.kit

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.nickstamp.kit.navigation.AppNavigation
import com.nickstamp.kit.di.appModule
import com.nickstamp.kit.feature.settings.di.settingsModule
import com.nickstamp.kit.feature.showcase.di.showcaseModule
import com.nickstamp.kit.core.helpers.di.helpersModule
import com.nickstamp.kit.ui.theme.AppTheme
import com.nickstamp.kit.ui.utils.EffectHandler
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(
            appModule,
            helpersModule,
            settingsModule,
            showcaseModule
        )
    }) {
        // Theme state management
        var isDarkTheme by remember { mutableStateOf(false) }
        
        AppTheme(useDarkTheme = isDarkTheme) {
            val navController = rememberNavController()
            val effectHandler: EffectHandler = koinInject()

            Scaffold(
                modifier = Modifier.background(colorScheme.background)
            ) {
                AppNavigation(
                    navController = navController,
                    effectHandler = effectHandler,
                    onThemeChange = { newTheme -> isDarkTheme = newTheme },
                    currentTheme = isDarkTheme
                )
            }
        }
    }
}