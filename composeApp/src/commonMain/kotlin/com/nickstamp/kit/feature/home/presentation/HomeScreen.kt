package com.nickstamp.kit.feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.feature.home.presentation.HomeContract.Event
import com.nickstamp.kit.feature.home.presentation.HomeContract.State
import com.nickstamp.kit.shared.composables.animatedSkeletonColor
import com.nickstamp.kit.ui.components.KitAppBar
import com.nickstamp.kit.ui.components.KitAppBarIcon
import com.nickstamp.kit.ui.components.KitSkeletonItem
import com.nickstamp.kit.ui.theme.AppTheme.colors
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import com.nickstamp.kit.ui.theme.AppTheme.typography
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.home_screen_title
import kit_cmp.composeapp.generated.resources.ic_settings
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreen(
    state: State,
    onEvent: (Event) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // App Bar with Settings Action
        KitAppBar(
            title = stringResource(Res.string.home_screen_title),
            actions = {
                KitAppBarIcon(
                    iconRes = Res.drawable.ic_settings,
                    onClickAction = { onEvent(Event.NavigateToSettings) }
                )
            }
        )

        // Main Content
        if (state.isLoading) {
            LoadingContent()
        } else if (state.error != null) {
            ErrorContent(
                error = state.error,
                onRetry = { onEvent(Event.RefreshData) }
            )
        } else {
            HomeContent(
                appName = state.appName,
                onEvent = onEvent,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun LoadingContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.large),
        verticalArrangement = Arrangement.spacedBy(spacing.medium)
    ) {
        val skeletonColor by animatedSkeletonColor()
        repeat(5) {
            KitSkeletonItem(
                color = { skeletonColor },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )
        }
    }
}

@Composable
private fun ErrorContent(
    error: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.large),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_settings),
            contentDescription = null,
            tint = colors.error,
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.height(spacing.medium))

        Text(
            text = "Error",
            style = typography.regular16,
            color = colors.error
        )

        Spacer(modifier = Modifier.height(spacing.small))

        Text(
            text = error,
            style = typography.bold14,
            color = colors.onSurface,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(spacing.large))

        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}

@Composable
private fun HomeContent(
    appName: String,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Welcome to $appName!",
            style = typography.bold20,
            color = colors.onSurface,
            textAlign = TextAlign.Center
        )
    }
}