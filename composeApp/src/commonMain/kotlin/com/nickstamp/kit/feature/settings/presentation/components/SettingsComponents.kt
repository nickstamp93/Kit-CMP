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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.nickstamp.kit.ui.theme.AppTheme.colors
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import com.nickstamp.kit.ui.theme.AppTheme.typography
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
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_dark_mode),
                    contentDescription = stringResource(Res.string.settings_theme_icon_content_description),
                    tint = colors.primary
                )
                Spacer(modifier = Modifier.width(spacing.medium))
                Column {
                    Text(
                        text = stringResource(Res.string.settings_dark_theme_title),
                        style = typography.bold16,
                        color = colors.onSurface
                    )
                    Text(
                        text = if (isDarkTheme) stringResource(Res.string.settings_dark_theme_enabled) else stringResource(
                            Res.string.settings_dark_theme_disabled
                        ),
                        style = typography.regular14,
                        color = colors.onSurfaceVariant
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
            containerColor = colors.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.large),
            verticalArrangement = Arrangement.spacedBy(spacing.medium)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_info),
                    contentDescription = stringResource(Res.string.settings_app_info_icon_content_description),
                    tint = colors.primary
                )
                Spacer(modifier = Modifier.width(spacing.medium))
                Text(
                    text = stringResource(Res.string.settings_app_info_title),
                    style = typography.bold16,
                    color = colors.onSurface
                )
            }

            Spacer(modifier = Modifier.height(spacing.small))

            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.small)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(Res.string.settings_app_info_name),
                        style = typography.regular14,
                        color = colors.onSurfaceVariant
                    )
                    Text(
                        text = stringResource(Res.string.app_name),
                        style = typography.bold14,
                        color = colors.onSurface
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(Res.string.settings_app_info_version),
                        style = typography.regular14,
                        color = colors.onSurfaceVariant
                    )
                    Text(
                        text = appVersion,
                        style = typography.bold14,
                        color = colors.onSurface
                    )
                }

                Spacer(modifier = Modifier.height(spacing.small))

                Text(
                    text = stringResource(Res.string.settings_app_info_developer),
                    style = typography.bold14,
                    color = colors.primary,
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
                    painter = painterResource(Res.drawable.ic_settings),
                    contentDescription = stringResource(Res.string.settings_developer_tools_icon_content_description),
                    tint = colors.secondary
                )
                Spacer(modifier = Modifier.width(spacing.medium))
                Column {
                    Text(
                        text = stringResource(Res.string.settings_developer_tools_title),
                        style = typography.bold16,
                        color = colors.onSurface
                    )
                    Text(
                        text = stringResource(Res.string.settings_developer_tools_description),
                        style = typography.regular14,
                        color = colors.onSurfaceVariant
                    )
                }
            }
            Spacer(modifier = Modifier.width(spacing.default))

            Button(
                onClick = { onNavigateToDeveloperTools() }
            ) {
                Text(stringResource(Res.string.settings_developer_tools_button))
            }
        }
    }
}