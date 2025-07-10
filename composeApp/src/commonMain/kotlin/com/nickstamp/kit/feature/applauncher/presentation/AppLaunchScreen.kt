package com.nickstamp.kit.feature.applauncher.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.feature.applauncher.domain.AppUpdateStatus
import com.nickstamp.kit.feature.applauncher.domain.AppUpdateStore
import com.nickstamp.kit.feature.applauncher.domain.AppUpdateType
import com.nickstamp.kit.feature.applauncher.presentation.AppLauncherContract.Event
import com.nickstamp.kit.feature.applauncher.presentation.AppLauncherContract.State
import com.nickstamp.kit.feature.config.domain.model.Announcement
import com.nickstamp.kit.feature.config.domain.model.CtaAction
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
fun AppLaunchScreen(
    state: State,
    onEvent: (Event) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            is State.Loading -> {
                LoadingContent()
            }

            is State.ConfigurationError -> {
                ConfigErrorContent(onEvent = onEvent)
            }

            is State.UpdateAvailable -> {
                AppUpdateContent(state = state, onEvent = onEvent)
            }

            is State.AnnouncementAvailable -> {
                AnnouncementContent(
                    announcement = state.announcement,
                    onCtaClick = {
                        onEvent(Event.OnAnnouncementCtaClick(state.announcement))
                    }
                )
            }
        }
    }
}

@Composable
private fun LoadingContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(Res.drawable.compose_multiplatform),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        CircularProgressIndicator(color = Color.White)
    }
}

@Composable
private fun ConfigErrorContent(onEvent: (Event) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(24.dp)
    ) {
        Text(
            text = "Configuration Error",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Failed to load app configuration",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.8f),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                onClick = { onEvent(Event.OnCloseAppClick) },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White
                )
            ) {
                Text("Close App")
            }
            Button(
                onClick = { onEvent(Event.OnTryAgainConfigClick) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("Try Again")
            }
        }
    }
}

@Composable
private fun AppUpdateContent(
    state: State.UpdateAvailable,
    onEvent: (Event) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(24.dp)
    ) {
        Text(
            text = stringResource(state.updateStatus.type.text),
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(state.updateStatus.type.message),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.8f),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (!state.updateStatus.isMandatoryUpdate) {
                OutlinedButton(
                    onClick = { onEvent(Event.OnUpdateLaterClick) },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White
                    )
                ) {
                    Text("Later")
                }
            }
            Button(
                onClick = { onEvent(Event.OnUpdateAppClick(state.updateStatus.store)) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("Update Now")
            }
        }
    }
}

@Composable
private fun AnnouncementContent(
    announcement: com.nickstamp.kit.feature.config.domain.model.Announcement,
    onCtaClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(24.dp)
    ) {
        Text(
            text = "Announcement",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = announcement.message,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.8f),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onCtaClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            val text = announcement.action.text.ifBlank { "OK" }
            Text(text = text)
        }
    }
}

private class AppLaunchPreviewStateProvider : PreviewParameterProvider<State> {
    override val values = sequenceOf(
        State.Loading,
        State.ConfigurationError,
        State.UpdateAvailable(
            updateStatus = AppUpdateStatus.UpdateAvailable(
                type = AppUpdateType.MANDATORY,
                store = AppUpdateStore.Google("")
            ),
            updateConfig = AppLauncherPreviewData.getAppUpdateConfig(minimumRequiredVersion = 2)

        ),
        State.UpdateAvailable(
            updateStatus = AppUpdateStatus.UpdateAvailable(
                type = AppUpdateType.OPTIONAL,
                store = AppUpdateStore.Google("")
            ),
            updateConfig = AppLauncherPreviewData.getAppUpdateConfig(latestVersionGoogle = 2)

        ),
        State.AnnouncementAvailable(
            announcement = Announcement(
                message = "This is an announcement",
                action = CtaAction(
                    text = "Download Now",
                    url = "www.url.com"
                ),
            )
        ),
        State.AnnouncementAvailable(
            announcement = Announcement(
                message = "This is a generic announcement",
                action = CtaAction(
                    text = "OK Got it",
                    url = ""
                )
            )
        )
    )
}

@Preview
@Composable
private fun AppLaunchScreenPreview(
    @PreviewParameter(AppLaunchPreviewStateProvider::class) state: State
) {
    AppLaunchScreen(
        state = state,
        onEvent = {}
    )
}