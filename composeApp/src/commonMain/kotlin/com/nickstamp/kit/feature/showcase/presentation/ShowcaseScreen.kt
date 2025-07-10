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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.shared.composables.animatedSkeletonColor
import com.nickstamp.kit.ui.components.IconGravity
import com.nickstamp.kit.ui.components.ImageSource.Companion.toImageSource
import com.nickstamp.kit.ui.components.KitAppBar
import com.nickstamp.kit.ui.components.KitAppBarIcon
import com.nickstamp.kit.ui.components.KitBannerAdPlaceholder
import com.nickstamp.kit.ui.components.KitButtonGroupRow
import com.nickstamp.kit.ui.components.KitEmptyDataViewCard
import com.nickstamp.kit.ui.components.KitEmptyResultsMessage
import com.nickstamp.kit.ui.components.KitFavoriteButton
import com.nickstamp.kit.ui.components.KitFilterButton
import com.nickstamp.kit.ui.components.KitFooterCard
import com.nickstamp.kit.ui.components.KitGroupButton
import com.nickstamp.kit.ui.components.KitGroupButtonSize
import com.nickstamp.kit.ui.components.KitHorizontalProgressBar
import com.nickstamp.kit.ui.components.KitIconButton
import com.nickstamp.kit.ui.components.KitInfoLabel
import com.nickstamp.kit.ui.components.KitKeyValueChip
import com.nickstamp.kit.ui.components.KitLabel
import com.nickstamp.kit.ui.components.KitNetworkImage
import com.nickstamp.kit.ui.components.KitPulsatingCircle
import com.nickstamp.kit.ui.components.KitSectionContainer
import com.nickstamp.kit.ui.components.KitSkeletonItem
import com.nickstamp.kit.ui.components.KitTextInput
import com.nickstamp.kit.ui.components.KitTextWithIcon
import com.nickstamp.kit.ui.components.KitVerticalProgressBar
import com.nickstamp.kit.ui.components.LabelSize
import com.nickstamp.kit.ui.components.SearchCatalogButtonRow
import com.nickstamp.kit.ui.theme.AppTheme
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.compose_multiplatform
import kit_cmp.composeapp.generated.resources.ic_back
import kit_cmp.composeapp.generated.resources.ic_info
import kit_cmp.composeapp.generated.resources.ic_settings
import kit_cmp.composeapp.generated.resources.ic_share
import kit_cmp.composeapp.generated.resources.ic_star_empty
import kit_cmp.composeapp.generated.resources.ic_star_filled

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowcaseScreen(
    state: ShowcaseContract.State,
    onEvent: (ShowcaseContract.Event) -> Unit
) {
    Scaffold(
        topBar = {
            KitAppBar(
                title = "Kit Components",
                onBack = { onEvent(ShowcaseContract.Event.OnBack) },
                logo = Res.drawable.compose_multiplatform.toImageSource()
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
                color = colorScheme.onSurface
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = AppTheme.spacing.medium),
                color = colorScheme.outline
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
                        backgroundColor = colorScheme.secondary,
                        textColor = colorScheme.onSecondary
                    )
                    KitInfoLabel(
                        text = "HOT",
                        backgroundColor = colorScheme.error,
                        textColor = colorScheme.onError
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
                        tint = colorScheme.error
                    )
                    KitIconButton(
                        icon = Res.drawable.ic_star_filled,
                        onClickAction = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitIconButton - Star"))
                        },
                        tint = colorScheme.primary
                    )
                    KitIconButton(
                        icon = Res.drawable.ic_info,
                        onClickAction = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitIconButton - Info"))
                        },
                        tint = colorScheme.secondary
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
                        tint = colorScheme.primary
                    )
                    KitAppBarIcon(
                        icon = Res.drawable.ic_settings,
                        onClickAction = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitAppBarIcon - Settings"))
                        },
                        tint = colorScheme.secondary
                    )
                    KitAppBarIcon(
                        icon = Res.drawable.ic_share,
                        onClickAction = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitAppBarIcon - Share"))
                        },
                        tint = colorScheme.tertiary
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
                        containerColor = colorScheme.surface
                    )
                ) {
                    KitAppBar(
                        title = "Demo App Bar",
                        onBack = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitAppBar - Navigation"))
                        },
                        actions = {
                            KitIconButton(
                                icon = Res.drawable.ic_back,
                                onClickAction = {
                                    onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitAppBar - Home Action"))
                                },
                                tint = colorScheme.onPrimary
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
                        color = colorScheme.error.copy(alpha = 0.3f)
                    )
                }
            }

            // KitTextInput Demo
            ComponentDemoCard(
                title = "KitTextInput",
                description = "Customizable text input with various configurations"
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.small)
                ) {
                    KitTextInput(
                        value = "Sample text",
                        onValueChange = {},
                        placeHolder = { Text("Enter text...") }
                    )
                    KitTextInput(
                        value = "",
                        onValueChange = {},
                        placeHolder = { Text("With icons...") },
                        leadingIcon = {
                            KitIconButton(
                                icon = Res.drawable.ic_info,
                                onClickAction = {}
                            )
                        },
                        trailingIcon = {
                            KitIconButton(
                                icon = Res.drawable.ic_settings,
                                onClickAction = {}
                            )
                        }
                    )
                }
            }

            // KitVerticalProgressBar Demo
            ComponentDemoCard(
                title = "KitVerticalProgressBar",
                description = "Vertical progress indicators with animation"
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    KitVerticalProgressBar(
                        progress = { 0.3f },
                        modifier = Modifier.height(100.dp)
                    )
                    KitVerticalProgressBar(
                        progress = { 0.7f },
                        modifier = Modifier.height(100.dp),
                        animate = true,
                        color = colorScheme.secondary
                    )
                    KitVerticalProgressBar(
                        progress = { 1.0f },
                        modifier = Modifier.height(100.dp),
                        width = 20.dp,
                        color = colorScheme.error
                    )
                }
            }

            // KitHorizontalProgressBar Demo
            ComponentDemoCard(
                title = "KitHorizontalProgressBar",
                description = "Horizontal progress indicators with animation"
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.small)
                ) {
                    KitHorizontalProgressBar(
                        progress = { 0.3f },
                        modifier = Modifier.fillMaxWidth()
                    )
                    KitHorizontalProgressBar(
                        progress = { 0.7f },
                        modifier = Modifier.fillMaxWidth(),
                        animate = true,
                        color = colorScheme.secondary
                    )
                    KitHorizontalProgressBar(
                        progress = { 1.0f },
                        modifier = Modifier.fillMaxWidth(),
                        height = 20.dp,
                        color = colorScheme.error
                    )
                }
            }

            // KitButtonGroup Demo
            ComponentDemoCard(
                title = "KitButtonGroup",
                description = "Grouped buttons with selection states"
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium)
                ) {
                    KitButtonGroupRow {
                        KitGroupButton(
                            text = "Option 1",
                            selected = true,
                            size = KitGroupButtonSize.Small,
                            onClick = {
                                onEvent(ShowcaseContract.Event.OnDemoButtonClick("ButtonGroup - Option 1"))
                            }
                        )
                        KitGroupButton(
                            text = "Option 2",
                            selected = false,
                            size = KitGroupButtonSize.Small,
                            onClick = {
                                onEvent(ShowcaseContract.Event.OnDemoButtonClick("ButtonGroup - Option 2"))
                            }
                        )
                        KitGroupButton(
                            text = "Option 3",
                            selected = false,
                            size = KitGroupButtonSize.Small,
                            onClick = {
                                onEvent(ShowcaseContract.Event.OnDemoButtonClick("ButtonGroup - Option 3"))
                            }
                        )
                    }

                    Text("Icon buttons require Material Icons which are not available in commonMain")
                }
            }

            // KitFilterButton Demo
            ComponentDemoCard(
                title = "KitFilterButton",
                description = "Filter buttons with dropdown indicator"
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small)
                ) {
                    KitFilterButton(
                        text = "Filter",
                        onClick = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("FilterButton - Filter"))
                        }
                    )
                    KitFilterButton(
                        text = "Sort",
                        onClick = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("FilterButton - Sort"))
                        }
                    )
                }
            }

            // KitEmptyResultsMessage Demo
            ComponentDemoCard(
                title = "KitEmptyResultsMessage",
                description = "Empty state message with icon"
            ) {
                KitEmptyResultsMessage(
                    icon = Res.drawable.ic_star_empty,
                    iconTint = colorScheme.primary
                )
            }

            // KitEmptyDataViewCard Demo
            ComponentDemoCard(
                title = "KitEmptyDataViewCard",
                description = "Empty data view with action button"
            ) {
                KitEmptyDataViewCard(
                    text = "No data available",
                    icon = Res.drawable.ic_info,
                    onTryAgainClick = {
                        onEvent(ShowcaseContract.Event.OnDemoButtonClick("EmptyDataView - Try Again"))
                    }
                )
            }

            // KitSectionContainer Demo
            ComponentDemoCard(
                title = "KitSectionContainer",
                description = "Section container with title and optional action"
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium)
                ) {
                    KitSectionContainer(
                        title = "Simple Section",
                        content = {
                            Text("Section content goes here")
                        }
                    )

                    KitSectionContainer(
                        title = "Section with Action",
                        action = {
                            Text(
                                "View All",
                                color = colorScheme.primary,
                                style = MaterialTheme.typography.labelMedium
                            )
                        },
                        content = {
                            Text("Section with action content")
                        }
                    )
                }
            }

            // SearchCatalogButtonRow Demo
            ComponentDemoCard(
                title = "SearchCatalogButtonRow",
                description = "Search and catalog button combination"
            ) {
                SearchCatalogButtonRow(
                    onClick = {
                        onEvent(ShowcaseContract.Event.OnDemoButtonClick("SearchCatalogButtonRow - Search"))
                    }
                )
            }

            // KitFooterCard Demo
            ComponentDemoCard(
                title = "KitFooterCard",
                description = "Footer card with background image and actions"
            ) {
                KitFooterCard(
                    title = "Want more?",
                    subtitle = "Browse the whole catalog",
                    primaryActionText = "Browse",
                    backgroundImageUrl = "https://static.vecteezy.com/system/resources/thumbnails/036/324/708/small/ai-generated-picture-of-a-tiger-walking-in-the-forest-photo.jpg",
                    primaryActionIcon = Res.drawable.ic_info,
                    onPrimaryAction = {
                        onEvent(ShowcaseContract.Event.OnDemoButtonClick("FooterCard - Browse"))
                    },
                    secondaryActionIcon = Res.drawable.ic_settings,
                    secondaryActionText = "Search",
                    onSecondaryActionText = {
                        onEvent(ShowcaseContract.Event.OnDemoButtonClick("FooterCard - Search"))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Interactive Demo Button
            Button(
                onClick = {
                    onEvent(ShowcaseContract.Event.OnFetchConfigurationClick)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("FETCH CONFIG")
            }
            Button(
                onClick = {
                    onEvent(ShowcaseContract.Event.OnRefreshConfigurationClick)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("REFRESH CONFIG")
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