package com.nickstamp.kit.feature.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.nickstamp.kit.feature.settings.presentation.SettingsContract
import com.nickstamp.kit.ui.theme.AppTheme
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.app_name
import kit_cmp.composeapp.generated.resources.ic_dark_mode
import kit_cmp.composeapp.generated.resources.ic_info
import kit_cmp.composeapp.generated.resources.ic_settings
import kit_cmp.composeapp.generated.resources.settings_app_info_developer
import kit_cmp.composeapp.generated.resources.settings_app_info_icon_content_description
import kit_cmp.composeapp.generated.resources.settings_app_info_name
import kit_cmp.composeapp.generated.resources.settings_app_info_title
import kit_cmp.composeapp.generated.resources.settings_app_info_version
import kit_cmp.composeapp.generated.resources.settings_dark_theme_disabled
import kit_cmp.composeapp.generated.resources.settings_dark_theme_enabled
import kit_cmp.composeapp.generated.resources.settings_dark_theme_title
import kit_cmp.composeapp.generated.resources.settings_developer_tools_button
import kit_cmp.composeapp.generated.resources.settings_developer_tools_description
import kit_cmp.composeapp.generated.resources.settings_developer_tools_icon_content_description
import kit_cmp.composeapp.generated.resources.settings_developer_tools_title
import kit_cmp.composeapp.generated.resources.settings_theme_icon_content_description
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ThemeSettingsCard(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
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
                    contentDescription = stringResource(Res.string.settings_theme_icon_content_description),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(AppTheme.spacing.medium))
                Column {
                    Text(
                        text = stringResource(Res.string.settings_dark_theme_title),
                        style = AppTheme.typography.bold16,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = if (isDarkTheme) stringResource(Res.string.settings_dark_theme_enabled) else stringResource(
                            Res.string.settings_dark_theme_disabled
                        ),
                        style = AppTheme.typography.regular14,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Switch(
                checked = isDarkTheme,
                onCheckedChange = { onToggleTheme() }
            )
        }
    }
}

@Composable
fun AppInfoCard(
    appVersion: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
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
                    contentDescription = stringResource(Res.string.settings_app_info_icon_content_description),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(AppTheme.spacing.medium))
                Text(
                    text = stringResource(Res.string.settings_app_info_title),
                    style = AppTheme.typography.bold16,
                    color = MaterialTheme.colorScheme.onSurface
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
                        text = stringResource(Res.string.settings_app_info_name),
                        style = AppTheme.typography.regular14,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = stringResource(Res.string.app_name),
                        style = AppTheme.typography.bold14,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(Res.string.settings_app_info_version),
                        style = AppTheme.typography.regular14,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = appVersion,
                        style = AppTheme.typography.bold14,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(modifier = Modifier.height(AppTheme.spacing.small))

                Text(
                    text = stringResource(Res.string.settings_app_info_developer),
                    style = AppTheme.typography.bold14,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun DeveloperToolsCard(
    onNavigateToDeveloperTools: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
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
                    painter = painterResource(Res.drawable.ic_settings),
                    contentDescription = stringResource(Res.string.settings_developer_tools_icon_content_description),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.width(AppTheme.spacing.medium))
                Column {
                    Text(
                        text = stringResource(Res.string.settings_developer_tools_title),
                        style = AppTheme.typography.bold16,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = stringResource(Res.string.settings_developer_tools_description),
                        style = AppTheme.typography.regular14,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Spacer(modifier = Modifier.width(AppTheme.spacing.default))

            Button(
                onClick = { onNavigateToDeveloperTools() }
            ) {
                Text(stringResource(Res.string.settings_developer_tools_button))
            }
        }
    }
}
