package com.nickstamp.kit.feature.developertools.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nickstamp.kit.ui.components.ImageSource.Companion.toImageSource
import com.nickstamp.kit.ui.components.KitAppBar
import com.nickstamp.kit.ui.theme.AppTheme.colors
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import com.nickstamp.kit.ui.theme.AppTheme.typography
import com.nickstamp.kit.ui.theme.PreviewWrapper
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.compose_multiplatform
import kit_cmp.composeapp.generated.resources.developer_tools_actions_title
import kit_cmp.composeapp.generated.resources.developer_tools_app_introduction_description
import kit_cmp.composeapp.generated.resources.developer_tools_app_introduction_title
import kit_cmp.composeapp.generated.resources.developer_tools_clear_all_preferences_button
import kit_cmp.composeapp.generated.resources.developer_tools_components_demo_icon_content_description
import kit_cmp.composeapp.generated.resources.developer_tools_current_configuration_title
import kit_cmp.composeapp.generated.resources.developer_tools_intro_icon_content_description
import kit_cmp.composeapp.generated.resources.developer_tools_kit_components_demo_description
import kit_cmp.composeapp.generated.resources.developer_tools_kit_components_demo_title
import kit_cmp.composeapp.generated.resources.developer_tools_title
import kit_cmp.composeapp.generated.resources.developer_tools_view_button
import kit_cmp.composeapp.generated.resources.developer_tools_view_configuration_button
import kit_cmp.composeapp.generated.resources.ic_completed
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeveloperToolsScreen(
    state: DeveloperToolsContract.State,
    onEvent: (DeveloperToolsContract.Event) -> Unit
) {
    Scaffold(
        topBar = {
            KitAppBar(
                title = stringResource(Res.string.developer_tools_title),
                onBack = { onEvent(DeveloperToolsContract.Event.OnBack) },
                logo = Res.drawable.compose_multiplatform.toImageSource()
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(spacing.default)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(spacing.large)
        ) {

            // Showcase Navigation
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = colors.surface
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing.large),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_completed),
                            contentDescription = stringResource(Res.string.developer_tools_components_demo_icon_content_description),
                            tint = colors.secondary
                        )
                        Spacer(modifier = Modifier.width(spacing.medium))
                        Column {
                            Text(
                                text = stringResource(Res.string.developer_tools_kit_components_demo_title),
                                style = typography.bold16,
                                color = colors.onSurface
                            )
                            Text(
                                text = stringResource(Res.string.developer_tools_kit_components_demo_description),
                                style = typography.regular14,
                                color = colors.onSurfaceVariant
                            )
                        }
                    }

                    Button(
                        onClick = { onEvent(DeveloperToolsContract.Event.NavigateToShowcase) }
                    ) {
                        Text(stringResource(Res.string.developer_tools_view_button))
                    }
                }
            }

            // Intro Screen Navigation
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = colors.surface
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing.large),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_completed),
                            contentDescription = stringResource(Res.string.developer_tools_intro_icon_content_description),
                            tint = colors.tertiary
                        )
                        Spacer(modifier = Modifier.width(spacing.medium))
                        Column {
                            Text(
                                text = stringResource(Res.string.developer_tools_app_introduction_title),
                                style = typography.bold16,
                                color = colors.onSurface
                            )
                            Text(
                                text = stringResource(Res.string.developer_tools_app_introduction_description),
                                style = typography.regular14,
                                color = colors.onSurfaceVariant
                            )
                        }
                    }

                    Button(
                        onClick = { onEvent(DeveloperToolsContract.Event.NavigateToIntro) }
                    ) {
                        Text(stringResource(Res.string.developer_tools_view_button))
                    }
                }
            }

            Text(
                text = stringResource(Res.string.developer_tools_actions_title),
                style = typography.bold20,
                color = colors.onSurface
            )

            // Clear Preferences Button
            Button(
                onClick = { onEvent(DeveloperToolsContract.Event.ClearAllPreferences) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(Res.string.developer_tools_clear_all_preferences_button))
            }

            // Configuration Button
            Button(
                onClick = { onEvent(DeveloperToolsContract.Event.ShowConfiguration) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(Res.string.developer_tools_view_configuration_button))
            }

            // Configuration Bottom Sheet
            if (state.showConfigurationSheet) {
                ModalBottomSheet(
                    onDismissRequest = { onEvent(DeveloperToolsContract.Event.HideConfiguration) },
                    sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.8f)
                            .padding(spacing.large)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = stringResource(Res.string.developer_tools_current_configuration_title),
                            style = typography.bold20,
                            color = colors.onSurface,
                            modifier = Modifier.padding(bottom = spacing.medium)
                        )

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = colors.surfaceVariant
                            )
                        ) {
                            Text(
                                text = state.configuration,
                                style = typography.regular12,
                                color = colors.onSurfaceVariant,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(spacing.medium)
                            )
                        }

                        Spacer(modifier = Modifier.height(spacing.large))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun DeveloperToolsScreenPreview() {
    PreviewWrapper {
        DeveloperToolsScreen(
            state = DeveloperToolsContract.State(),
            onEvent = {}
        )
    }
}