package com.nickstamp.kit.di

import androidx.compose.runtime.Composable
import org.koin.compose.KoinApplication
import com.nickstamp.kit.core.di.coreModule
import com.nickstamp.kit.feature.settings.di.settingsModule
import com.nickstamp.kit.feature.showcase.di.showcaseModule
import com.nickstamp.kit.feature.config.di.configModule

@Composable
actual fun KoinConfig(content: @Composable () -> Unit) {
    // iOS uses MainViewController startKoin, but provide fallback for previews
    KoinApplication(application = {
        modules(
            appModule,
            coreModule,
            configModule,
            settingsModule,
            showcaseModule
        )
    }) {
        content()
    }
}