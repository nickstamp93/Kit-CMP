package com.nickstamp.kit.presentation.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nickstamp.kit.presentation.theme.AppTheme
import com.nickstamp.kit.presentation.theme.PreviewWrapper
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.ic_completed
import kit_cmp.composeapp.generated.resources.ic_dark_mode
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SettingsScreen(
    state: SettingsContract.State,
    onEvent: (SettingsContract.Event) -> Unit
) {
    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize()
            .padding(AppTheme.spacing.default)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.large)
    ) {
        Text(
            text = "Settings",
            style = AppTheme.typography.bold24,
            color = colorScheme.onSurface
        )

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

        // Component Demo Navigation
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
                    onClick = { onEvent(SettingsContract.Event.NavigateToShowcase) }
                ) {
                    Text("View")
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
