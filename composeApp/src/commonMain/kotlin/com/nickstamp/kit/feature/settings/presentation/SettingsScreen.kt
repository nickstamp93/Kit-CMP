package com.nickstamp.kit.feature.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.nickstamp.kit.ui.components.ImageSource.Companion.toImageSource
import com.nickstamp.kit.ui.components.KitAppBar
import com.nickstamp.kit.ui.theme.AppTheme
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import com.nickstamp.kit.ui.theme.PreviewWrapper
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.app_name
import kit_cmp.composeapp.generated.resources.compose_multiplatform
import kit_cmp.composeapp.generated.resources.ic_completed
import kit_cmp.composeapp.generated.resources.ic_dark_mode
import kit_cmp.composeapp.generated.resources.ic_info
import org.jetbrains.compose.resources.painterResource
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
                title = "Settings",
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

            // Theme Setting Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = colorScheme.surface
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.spacing.large),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_dark_mode),
                            contentDescription = "Theme icon",
                            tint = colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(AppTheme.spacing.medium))
                        Column {
                            Text(
                                text = "Dark Theme",
                                style = AppTheme.typography.bold16,
                                color = colorScheme.onSurface
                            )
                            Text(
                                text = if (state.isDarkTheme) "Dark mode is enabled" else "Light mode is enabled",
                                style = AppTheme.typography.regular14,
                                color = colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Switch(
                        checked = state.isDarkTheme,
                        onCheckedChange = { onEvent(SettingsContract.Event.ToggleTheme) }
                    )
                }
            }

            // App Info Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.spacing.large),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_info),
                            contentDescription = "App info icon",
                            tint = colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(AppTheme.spacing.medium))
                        Text(
                            text = "App Information",
                            style = AppTheme.typography.bold16,
                            color = colorScheme.onSurface
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(AppTheme.spacing.small))
                    
                    Column(
                        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.small)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "App Name:",
                                style = AppTheme.typography.regular14,
                                color = colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = stringResource(Res.string.app_name),
                                style = AppTheme.typography.bold14,
                                color = colorScheme.onSurface
                            )
                        }
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Version:",
                                style = AppTheme.typography.regular14,
                                color = colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = state.appVersion,
                                style = AppTheme.typography.bold14,
                                color = colorScheme.onSurface
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(AppTheme.spacing.small))
                        
                        Text(
                            text = "Developed by Nick Stampoulis",
                            style = AppTheme.typography.bold14,
                            color = colorScheme.primary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            // Developer Tools Navigation
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = colorScheme.surface
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.spacing.large),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_completed),
                            contentDescription = "Developer tools icon",
                            tint = colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.width(spacing.medium))
                        Column {
                            Text(
                                text = "Developer Tools",
                                style = AppTheme.typography.bold16,
                                color = colorScheme.onSurface
                            )
                            Text(
                                text = "Access development features and utilities",
                                style = AppTheme.typography.regular14,
                                color = colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(spacing.default))

                    Button(
                        onClick = { onEvent(SettingsContract.Event.NavigateToDeveloperTools) }
                    ) {
                        Text("Open")
                    }
                }
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
