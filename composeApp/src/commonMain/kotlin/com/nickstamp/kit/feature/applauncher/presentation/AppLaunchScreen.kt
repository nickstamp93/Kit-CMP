package com.nickstamp.kit.feature.applauncher.presentation

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.feature.applauncher.domain.AppUpdateStatus
import com.nickstamp.kit.feature.applauncher.domain.AppUpdateStore
import com.nickstamp.kit.feature.applauncher.domain.AppUpdateType
import com.nickstamp.kit.feature.applauncher.presentation.AppLauncherContract.Event
import com.nickstamp.kit.feature.applauncher.presentation.AppLauncherContract.State
import com.nickstamp.kit.feature.config.domain.model.Announcement
import com.nickstamp.kit.feature.config.domain.model.CtaAction
import com.nickstamp.kit.shared.composables.animatedOpacity
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import com.nickstamp.kit.ui.theme.AppTheme.typography
import com.nickstamp.kit.ui.theme.highEmphasis
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.announcement
import kit_cmp.composeapp.generated.resources.close_app
import kit_cmp.composeapp.generated.resources.compose_multiplatform
import kit_cmp.composeapp.generated.resources.fetch_config_error
import kit_cmp.composeapp.generated.resources.fetch_config_error_message
import kit_cmp.composeapp.generated.resources.ic_announcement
import kit_cmp.composeapp.generated.resources.ic_fetch_error
import kit_cmp.composeapp.generated.resources.ic_refresh
import kit_cmp.composeapp.generated.resources.ic_update
import kit_cmp.composeapp.generated.resources.ic_update_available
import kit_cmp.composeapp.generated.resources.later
import kit_cmp.composeapp.generated.resources.ok_caps
import kit_cmp.composeapp.generated.resources.try_again
import kit_cmp.composeapp.generated.resources.update_now
import org.jetbrains.compose.resources.DrawableResource
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
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorScheme.primary)
            .padding(horizontal = spacing.large)
    ) {
        when (state) {

            is State.ConfigurationError -> {
                ConfigErrorContent(
                    onEvent = onEvent,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            is State.Loading -> {
                LoadingContent()
            }

            is State.UpdateAvailable -> {
                AppUpdateContent(
                    state = state,
                    onEvent = onEvent,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            is State.AnnouncementAvailable -> {
                AnnouncementContent(
                    announcement = state.announcement,
                    onCtaClick = {
                        onEvent(Event.OnAnnouncementCtaClick(state.announcement))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun AppUpdateContent(
    state: State.UpdateAvailable,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {

    MessageWithActionsContent(
        imageRes = Res.drawable.ic_update_available,
        title = stringResource(state.updateStatus.type.text),
        message = stringResource(state.updateStatus.type.message),
        onLeftButtonClick = {
            if (state.updateStatus.isMandatoryUpdate) {
                onEvent(Event.OnCloseAppClick)
            } else {
                onEvent(Event.OnUpdateLaterClick)
            }
        },
        onRightButtonClick = {
            onEvent(Event.OnUpdateAppClick(state.updateStatus.store))
        },
        leftButtonContent = {
            val textRes = if (state.updateStatus.isMandatoryUpdate) {
                Res.string.close_app
            } else {
                Res.string.later
            }
            Text(text = stringResource(textRes))
        },
        rightButtonContent = {
            Icon(
                painter = painterResource(Res.drawable.ic_update),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(spacing.default))
            Text(text = stringResource(Res.string.update_now))
        },
        modifier = modifier
    )
}

@Composable
private fun LoadingContent(
    modifier: Modifier = Modifier
) {
    val alpha by animatedOpacity()
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(Res.drawable.compose_multiplatform),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(288.dp)
                .graphicsLayer {
                    this.alpha = alpha
                }
        )
    }
}

@Composable
private fun ConfigErrorContent(
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    MessageWithActionsContent(
        imageRes = Res.drawable.ic_fetch_error,
        title = stringResource(Res.string.fetch_config_error),
        message = stringResource(Res.string.fetch_config_error_message),
        onLeftButtonClick = {
            onEvent(Event.OnCloseAppClick)
        },
        onRightButtonClick = {
            onEvent(Event.OnTryAgainConfigClick)
        },
        leftButtonContent = {
            Text(text = stringResource(Res.string.close_app))
        },
        rightButtonContent = {
            Icon(
                painter = painterResource(Res.drawable.ic_refresh),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(spacing.default))
            Text(text = stringResource(Res.string.try_again))
        },
        modifier = modifier
    )
}

@Composable
fun AnnouncementContent(
    announcement: Announcement,
    onCtaClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Image(
            painter = painterResource(Res.drawable.ic_announcement),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(colorScheme.onPrimary),
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(spacing.large))

        Text(
            text = stringResource(Res.string.announcement),
            style = typography.bold16,
            color = colorScheme.onPrimary,
        )

        Spacer(modifier = Modifier.height(spacing.small))

        Text(
            text = announcement.message,
            style = typography.regular14,
            color = colorScheme.onPrimary.highEmphasis(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(spacing.xlarge))

        Button(
            onClick = onCtaClick,
            colors = ButtonDefaults.buttonColors(
                contentColor = colorScheme.onSecondary,
                containerColor = colorScheme.secondary.highEmphasis()
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            val text = announcement.action.text.ifBlank {
                stringResource(Res.string.ok_caps)
            }
            Text(text = text)
        }
    }
}

@Composable
private fun MessageWithActionsContent(
    imageRes: DrawableResource,
    title: String,
    message: String,
    onLeftButtonClick: () -> Unit,
    onRightButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    leftButtonContent: @Composable () -> Unit,
    rightButtonContent: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(colorScheme.onPrimary),
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(spacing.large))

        Text(
            text = title,
            style = typography.bold16,
            color = colorScheme.onPrimary,
        )

        Spacer(modifier = Modifier.height(spacing.small))

        Text(
            text = message,
            style = typography.regular14,
            color = colorScheme.onPrimary.highEmphasis(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(spacing.xlarge))

        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.default),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = onLeftButtonClick,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = colorScheme.onPrimary,
                    containerColor = Color.Transparent
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = colorScheme.onPrimary
                ),
                modifier = Modifier.weight(1f)
            ) {
                leftButtonContent()
            }
            Button(
                onClick = onRightButtonClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorScheme.onSecondary,
                    containerColor = colorScheme.secondary.highEmphasis()
                ),
                modifier = Modifier.weight(1f)
            ) {
                rightButtonContent()
            }
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
            updateConfig = AppLauncherPreviewData.getAppUpdateConfig(androidMinimumRequiredVersion = 2)

        ),
        State.UpdateAvailable(
            updateStatus = AppUpdateStatus.UpdateAvailable(
                type = AppUpdateType.OPTIONAL,
                store = AppUpdateStore.Google("")
            ),
            updateConfig = AppLauncherPreviewData.getAppUpdateConfig(androidLatestVersion = 2)

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