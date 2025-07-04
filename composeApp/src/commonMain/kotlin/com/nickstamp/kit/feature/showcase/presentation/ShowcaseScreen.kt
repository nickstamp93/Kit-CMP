package com.nickstamp.kit.feature.showcase.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.presentation.theme.AppTheme
import com.nickstamp.kit.shared.components.IconGravity
import com.nickstamp.kit.shared.components.ImageSource.Companion.toImageSource
import com.nickstamp.kit.shared.components.KitAppBar
import com.nickstamp.kit.shared.components.KitAppBarIcon
import com.nickstamp.kit.shared.components.KitBannerAdPlaceholder
import com.nickstamp.kit.shared.components.KitFavoriteButton
import com.nickstamp.kit.shared.components.KitIconButton
import com.nickstamp.kit.shared.components.KitInfoLabel
import com.nickstamp.kit.shared.components.KitKeyValueChip
import com.nickstamp.kit.shared.components.KitLabel
import com.nickstamp.kit.shared.components.KitNetworkImage
import com.nickstamp.kit.shared.components.KitPulsatingCircle
import com.nickstamp.kit.shared.components.KitSkeletonItem
import com.nickstamp.kit.shared.components.KitTextWithIcon
import com.nickstamp.kit.shared.components.LabelSize
import com.nickstamp.kit.shared.composables.animatedSkeletonColor
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.ic_back
import kit_cmp.composeapp.generated.resources.ic_info
import kit_cmp.composeapp.generated.resources.ic_settings
import kit_cmp.composeapp.generated.resources.ic_share
import kit_cmp.composeapp.generated.resources.ic_star_empty
import kit_cmp.composeapp.generated.resources.ic_star_filled
import org.jetbrains.compose.resources.painterResource

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
                            painter = painterResource(Res.drawable.ic_back),
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
                        icon = Res.drawable.ic_star_empty,
                        onClickAction = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitIconButton - Favorite"))
                        },
                        tint = MaterialTheme.colorScheme.error
                    )
                    KitIconButton(
                        icon = Res.drawable.ic_star_filled,
                        onClickAction = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitIconButton - Star"))
                        },
                        tint = MaterialTheme.colorScheme.primary
                    )
                    KitIconButton(
                        icon = Res.drawable.ic_info,
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
                    val skeletonColor by animatedSkeletonColor()
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        KitSkeletonItem(
                            width = 60.dp,
                            height = 60.dp,
                            shape = CircleShape,
                            color = { skeletonColor }
                        )
                        Column {
                            KitSkeletonItem(
                                width = 120.dp,
                                height = 16.dp,
                                color = { skeletonColor }
                            )
                            Spacer(modifier = Modifier.height(AppTheme.spacing.xsmall))
                            KitSkeletonItem(
                                width = 80.dp,
                                height = 12.dp,
                                color = { skeletonColor }
                            )
                        }
                    }
                    KitSkeletonItem(
                        modifier = Modifier.fillMaxWidth(),
                        height = 100.dp,
                        shape = AppTheme.shapes.large,
                        color = { skeletonColor }
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
                        icon = Res.drawable.ic_info,
                        onClickAction = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitAppBarIcon - Menu"))
                        },
                        tint = MaterialTheme.colorScheme.primary
                    )
                    KitAppBarIcon(
                        icon = Res.drawable.ic_settings,
                        onClickAction = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitAppBarIcon - Settings"))
                        },
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    KitAppBarIcon(
                        icon = Res.drawable.ic_share,
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
                        imageSource = "https://picsum.photos/100/100?random=1".toImageSource(),
                        modifier = Modifier.height(80.dp).width(80.dp),
                        shape = AppTheme.shapes.default
                    )
                    KitNetworkImage(
                        imageSource = "https://picsum.photos/100/100?random=2".toImageSource(),
                        modifier = Modifier.height(80.dp).width(80.dp),
                        shape = CircleShape
                    )
                    KitNetworkImage(
                        imageSource = "https://picsum.photos/100/100?random=3".toImageSource(),
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
                                icon = Res.drawable.ic_back,
                                onClickAction = {
                                    onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitAppBar - Home Action"))
                                },
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    )
                }
            }

            // KitFavoriteButton Demo
            ComponentDemoCard(
                title = "KitFavoriteButton",
                description = "Animated favorite button with rotation and scale effects"
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    KitFavoriteButton(
                        isFavorite = false,
                        onClick = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitFavoriteButton - Not Favorite"))
                        }
                    )
                    KitFavoriteButton(
                        isFavorite = true,
                        onClick = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitFavoriteButton - Favorite"))
                        }
                    )
                }
            }

            // KitLabel Demo
            ComponentDemoCard(
                title = "KitLabel",
                description = "Styled labels with icons and different sizes"
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.small)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        KitLabel(text = "Small", size = LabelSize.SMALL)
                        KitLabel(text = "Large", size = LabelSize.LARGE)
                        KitLabel(
                            text = "With Icon",
                            iconStart = Res.drawable.ic_star_filled,
                            size = LabelSize.SMALL
                        )
                        KitLabel(
                            text = "With Icon",
                            iconEnd = Res.drawable.ic_star_filled,
                            size = LabelSize.SMALL
                        )
                        KitLabel(
                            text = "With Icon",
                            iconStart = Res.drawable.ic_star_filled,
                            iconEnd = Res.drawable.ic_star_filled,
                            size = LabelSize.SMALL
                        )
                    }
                }
            }

            // KitTextWithIcon Demo
            ComponentDemoCard(
                title = "KitTextWithIcon",
                description = "Text with icons in different positions"
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.small)
                ) {
                    KitTextWithIcon(
                        text = "Icon Start",
                        icon = Res.drawable.ic_back,
                        iconGravity = IconGravity.START
                    )
                    KitTextWithIcon(
                        text = "Icon End",
                        icon = Res.drawable.ic_info,
                        iconGravity = IconGravity.END
                    )
                }
            }

            // KitBannerAdPlaceholder Demo
            ComponentDemoCard(
                title = "KitBannerAdPlaceholder",
                description = "Simple advertisement placeholder"
            ) {
                KitBannerAdPlaceholder()
            }

            // KitPulsatingCircle Demo
            ComponentDemoCard(
                title = "KitPulsatingCircle",
                description = "Animated pulsating circle effect"
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    KitPulsatingCircle(
                        modifier = Modifier.size(60.dp)
                    )
                    KitPulsatingCircle(
                        modifier = Modifier.size(40.dp),
                        color = MaterialTheme.colorScheme.error.copy(alpha = 0.3f)
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
            containerColor = colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.spacing.large)
        ) {
            Text(
                text = title,
                style = AppTheme.typography.bold16,
                color = colorScheme.onSurface
            )
            Text(
                text = description,
                style = AppTheme.typography.regular12,
                color = colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = AppTheme.spacing.medium)
            )
            content()
        }
    }
}