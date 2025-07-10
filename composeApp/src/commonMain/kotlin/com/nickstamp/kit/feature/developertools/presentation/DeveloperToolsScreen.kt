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
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nickstamp.kit.ui.components.ImageSource.Companion.toImageSource
import com.nickstamp.kit.ui.components.KitAppBar
import com.nickstamp.kit.ui.theme.AppTheme
import com.nickstamp.kit.ui.theme.PreviewWrapper
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.compose_multiplatform
import kit_cmp.composeapp.generated.resources.ic_completed
import org.jetbrains.compose.resources.painterResource
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
                title = "Developer Tools",
                onBack = { onEvent(DeveloperToolsContract.Event.OnBack) },
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

        // Showcase Navigation
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
                        contentDescription = "Components demo icon",
                        tint = colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.width(AppTheme.spacing.medium))
                    Column {
                        Text(
                            text = "Kit Components Demo",
                            style = AppTheme.typography.bold16,
                            color = colorScheme.onSurface
                        )
                        Text(
                            text = "View framework UI components",
                            style = AppTheme.typography.regular14,
                            color = colorScheme.onSurfaceVariant
                        )
                    }
                }

                Button(
                    onClick = { onEvent(DeveloperToolsContract.Event.NavigateToShowcase) }
                ) {
                    Text("View")
                }
            }
        }

        // Intro Screen Navigation
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
                        contentDescription = "Intro icon",
                        tint = colorScheme.tertiary
                    )
                    Spacer(modifier = Modifier.width(AppTheme.spacing.medium))
                    Column {
                        Text(
                            text = "App Introduction",
                            style = AppTheme.typography.bold16,
                            color = colorScheme.onSurface
                        )
                        Text(
                            text = "View the app onboarding flow",
                            style = AppTheme.typography.regular14,
                            color = colorScheme.onSurfaceVariant
                        )
                    }
                }

                Button(
                    onClick = { onEvent(DeveloperToolsContract.Event.NavigateToIntro) }
                ) {
                    Text("View")
                }
            }
        }

        Text(
            text = "Actions",
            style = AppTheme.typography.bold20,
            color = colorScheme.onSurface
        )

        // Clear Preferences Button
        Button(
            onClick = { onEvent(DeveloperToolsContract.Event.ClearAllPreferences) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clear All Preferences")
        }

        // Configuration Button
        Button(
            onClick = { onEvent(DeveloperToolsContract.Event.ShowConfiguration) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Configuration")
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
                        .padding(AppTheme.spacing.large)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Current Configuration",
                        style = AppTheme.typography.bold20,
                        color = colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = AppTheme.spacing.medium)
                    )
                    
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = colorScheme.surfaceVariant
                        )
                    ) {
                        Text(
                            text = state.configuration,
                            style = AppTheme.typography.regular12,
                            color = colorScheme.onSurfaceVariant,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(AppTheme.spacing.medium)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(AppTheme.spacing.large))
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