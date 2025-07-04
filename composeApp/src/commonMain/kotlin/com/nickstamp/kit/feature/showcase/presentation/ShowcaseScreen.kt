package com.nickstamp.kit.feature.showcase.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.presentation.theme.AppTheme
import com.nickstamp.kit.shared.components.KitAppBar
import com.nickstamp.kit.shared.components.KitAppBarIcon
import com.nickstamp.kit.shared.components.KitIconButton
import com.nickstamp.kit.shared.components.KitInfoLabel
import com.nickstamp.kit.shared.components.KitKeyValueChip
import com.nickstamp.kit.shared.components.KitNetworkImage
import com.nickstamp.kit.shared.components.KitSkeletonItem
import com.nickstamp.kit.shared.components.ImageSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowcaseScreen(
    state: ShowcaseContract.State,
    onEvent: (ShowcaseContract.Event) -> Unit,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Kit Components") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(AppTheme.spacing.large)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.large)
        ) {
        Text(
            text = "Kit Framework Components",
            style = AppTheme.typography.bold24,
            color = MaterialTheme.colorScheme.onSurface
        )
        
        HorizontalDivider(
            modifier = Modifier.padding(vertical = AppTheme.spacing.medium),
            color = MaterialTheme.colorScheme.outline
        )

        // KitInfoLabel Demo
        ComponentDemoCard(
            title = "KitInfoLabel",
            description = "Styled labels for information display"
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                KitInfoLabel(text = "NEW")
                KitInfoLabel(
                    text = "BETA",
                    backgroundColor = MaterialTheme.colorScheme.secondary,
                    textColor = MaterialTheme.colorScheme.onSecondary
                )
                KitInfoLabel(
                    text = "HOT",
                    backgroundColor = MaterialTheme.colorScheme.error,
                    textColor = MaterialTheme.colorScheme.onError
                )
            }
        }

        // KitKeyValueChip Demo
        ComponentDemoCard(
            title = "KitKeyValueChip",
            description = "Key-value pairs with styled containers"
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.small)
            ) {
                KitKeyValueChip(caption = "Status", value = "Active")
                KitKeyValueChip(caption = "Version", value = "1.0.0")
                KitKeyValueChip(caption = "Build", value = "Release")
            }
        }

        // KitIconButton Demo
        ComponentDemoCard(
            title = "KitIconButton",
            description = "Interactive icon buttons with ripple effects"
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                KitIconButton(
                    imageVector = Icons.Default.Favorite,
                    onClickAction = { 
                        onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitIconButton - Favorite")) 
                    },
                    tint = MaterialTheme.colorScheme.error
                )
                KitIconButton(
                    imageVector = Icons.Default.Star,
                    onClickAction = { 
                        onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitIconButton - Star")) 
                    },
                    tint = MaterialTheme.colorScheme.primary
                )
                KitIconButton(
                    imageVector = Icons.Default.Info,
                    onClickAction = { 
                        onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitIconButton - Info")) 
                    },
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }

        // KitSkeletonItem Demo
        ComponentDemoCard(
            title = "KitSkeletonItem",
            description = "Animated loading placeholders with various shapes"
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.small)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    KitSkeletonItem(
                        width = 60.dp,
                        height = 60.dp,
                        shape = CircleShape
                    )
                    Column {
                        KitSkeletonItem(
                            width = 120.dp,
                            height = 16.dp
                        )
                        Spacer(modifier = Modifier.height(AppTheme.spacing.xsmall))
                        KitSkeletonItem(
                            width = 80.dp,
                            height = 12.dp
                        )
                    }
                }
                KitSkeletonItem(
                    modifier = Modifier.fillMaxWidth(),
                    height = 100.dp,
                    shape = AppTheme.shapes.large
                )
            }
        }

        // KitAppBarIcon Demo
        ComponentDemoCard(
            title = "KitAppBarIcon",
            description = "App bar navigation icons with custom styling"
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                KitAppBarIcon(
                    imageVector = Icons.Default.Menu,
                    onClickAction = { 
                        onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitAppBarIcon - Menu")) 
                    },
                    tint = MaterialTheme.colorScheme.primary
                )
                KitAppBarIcon(
                    imageVector = Icons.Default.Settings,
                    onClickAction = { 
                        onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitAppBarIcon - Settings")) 
                    },
                    tint = MaterialTheme.colorScheme.secondary
                )
                KitAppBarIcon(
                    imageVector = Icons.Default.Share,
                    onClickAction = { 
                        onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitAppBarIcon - Share")) 
                    },
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
        }

        // KitNetworkImage Demo
        ComponentDemoCard(
            title = "KitNetworkImage",
            description = "Network image loading with shape support"
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                KitNetworkImage(
                    imageUrl = "https://picsum.photos/100/100?random=1",
                    modifier = Modifier.height(80.dp).width(80.dp),
                    shape = AppTheme.shapes.default
                )
                KitNetworkImage(
                    imageUrl = "https://picsum.photos/100/100?random=2",
                    modifier = Modifier.height(80.dp).width(80.dp),
                    shape = CircleShape
                )
                KitNetworkImage(
                    imageUrl = "https://picsum.photos/100/100?random=3",
                    modifier = Modifier.height(80.dp).width(80.dp),
                    shape = AppTheme.shapes.large
                )
            }
        }

        // KitAppBar Demo
        ComponentDemoCard(
            title = "KitAppBar",
            description = "Complete app bar with navigation and actions"
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                KitAppBar(
                    title = "Demo App Bar",
                    navigateUp = { 
                        onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitAppBar - Navigation")) 
                    },
                    actions = {
                        KitIconButton(
                            imageVector = Icons.Default.Home,
                            onClickAction = { 
                                onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitAppBar - Home Action")) 
                            },
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                )
            }
        }

        // Interactive Demo Button
        Button(
            onClick = { 
                onEvent(ShowcaseContract.Event.OnDemoButtonClick("All Components Demonstrated!")) 
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Test Component Interactions")
        }
        }
    }
}

@Composable
fun ComponentDemoCard(
    title: String,
    description: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.spacing.large)
        ) {
            Text(
                text = title,
                style = AppTheme.typography.bold16,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = description,
                style = AppTheme.typography.regular12,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = AppTheme.spacing.medium)
            )
            content()
        }
    }
}