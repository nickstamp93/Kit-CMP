package com.nickstamp.kit.feature.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nickstamp.kit.feature.settings.presentation.components.AppInfoCard
import com.nickstamp.kit.feature.settings.presentation.components.DeveloperToolsCard
import com.nickstamp.kit.feature.settings.presentation.components.ThemeSettingsCard
import com.nickstamp.kit.ui.components.ImageSource.Companion.toImageSource
import com.nickstamp.kit.ui.components.KitAppBar
import com.nickstamp.kit.ui.components.KitSectionContainer
import com.nickstamp.kit.ui.theme.AppTheme
import com.nickstamp.kit.ui.theme.PreviewWrapper
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.compose_multiplatform
import kit_cmp.composeapp.generated.resources.settings_developer_tools_title
import kit_cmp.composeapp.generated.resources.settings_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SettingsScreen(
    state: SettingsContract.State,
    onEvent: (SettingsContract.Event) -> Unit
) {
    Scaffold(
        topBar = {
            KitAppBar(
                title = stringResource(Res.string.settings_title),
                logo = Res.drawable.compose_multiplatform.toImageSource()
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(AppTheme.spacing.default)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.large)
        ) {
            ThemeSettingsCard(
                isDarkTheme = state.isDarkTheme,
                onToggleTheme = { onEvent(SettingsContract.Event.ToggleTheme) }
            )

            AppInfoCard(
                appVersion = state.appVersion
            )

            KitSectionContainer(
                title = stringResource(Res.string.settings_developer_tools_title)
            ) {
                DeveloperToolsCard(
                    onNavigateToDeveloperTools = { onEvent(SettingsContract.Event.NavigateToDeveloperTools) }
                )
            }
        }
    }
}


@Preview
@Composable
private fun SettingsScreenPreview() {
    PreviewWrapper {
        SettingsScreen(
            state = SettingsContract.State(),
            onEvent = {},
        )
    }
}
