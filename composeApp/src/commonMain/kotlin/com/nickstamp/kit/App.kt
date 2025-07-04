package com.nickstamp.kit

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nickstamp.kit.di.appModule
import com.nickstamp.kit.feature.home.di.homeModule
import com.nickstamp.kit.feature.home.presentation.HomeScreenRoute
import com.nickstamp.kit.shared.utils.EffectHandler
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(
            appModule,
            homeModule
        )
    }) {
        MaterialTheme {
            val navController = rememberNavController()
            val effectHandler: EffectHandler = koinInject()

            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home") {
                    HomeScreenRoute(
                        effectHandler = effectHandler
                    )
                }
            }
        }
    }
}