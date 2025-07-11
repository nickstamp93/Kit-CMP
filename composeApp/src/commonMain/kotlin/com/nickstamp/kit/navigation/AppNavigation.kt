package com.nickstamp.kit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nickstamp.kit.feature.applauncher.presentation.AppLauncherScreenRoute
import com.nickstamp.kit.feature.developertools.presentation.DeveloperToolsScreenRoute
import com.nickstamp.kit.feature.home.presentation.HomeScreenRoute
import com.nickstamp.kit.feature.intro.presentation.IntroScreenRoute
import com.nickstamp.kit.feature.settings.presentation.SettingsScreenRoute
import com.nickstamp.kit.feature.showcase.presentation.ShowcaseScreenRoute
import com.nickstamp.kit.ui.utils.EffectHandler

@Composable
fun AppNavigation(
    navController: NavHostController,
    effectHandler: EffectHandler,
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.AppLauncher
    ) {
        composable<AppRoutes.Home> {
            HomeScreenRoute(
                onNavigateToSettings = {
                    navController.navigate(AppRoutes.Settings)
                },
                effectHandler = effectHandler
            )
        }

        composable<AppRoutes.Settings> {
            SettingsScreenRoute(
                onNavigateBack = { navController.popBackStack() },
                effectHandler = effectHandler,
                onNavigateToDeveloperTools = {
                    navController.navigate(AppRoutes.DeveloperTools)
                }
            )
        }

        composable<AppRoutes.Showcase> {
            ShowcaseScreenRoute(
                onNavigateBack = { navController.popBackStack() },
                effectHandler = effectHandler
            )
        }

        composable<AppRoutes.Intro> {
            IntroScreenRoute(
                onNavigateToHome = {
                    navController.navigate(AppRoutes.Home) {
                        popUpTo(AppRoutes.Intro) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                effectHandler = effectHandler
            )
        }

        composable<AppRoutes.DeveloperTools> {
            DeveloperToolsScreenRoute(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToShowcase = {
                    navController.navigate(AppRoutes.Showcase)
                },
                onNavigateToIntro = {
                    navController.navigate(AppRoutes.Intro)
                },
                effectHandler = effectHandler
            )
        }

        composable<AppRoutes.AppLauncher> {
            AppLauncherScreenRoute(
                onNavigateToHome = {
                    navController.navigate(AppRoutes.Home) {
                        popUpTo(AppRoutes.AppLauncher) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onNavigateToIntro = {
                    navController.navigate(AppRoutes.Intro) {
                        popUpTo(AppRoutes.AppLauncher) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                effectHandler = effectHandler
            )
        }
    }
}

