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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import com.nickstamp.kit.ui.theme.AppTheme.colors
import com.nickstamp.kit.ui.theme.AppTheme.shapes
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import com.nickstamp.kit.ui.theme.AppTheme.typography
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.compose_multiplatform
import kit_cmp.composeapp.generated.resources.ic_back
import kit_cmp.composeapp.generated.resources.ic_info
import kit_cmp.composeapp.generated.resources.ic_settings
import kit_cmp.composeapp.generated.resources.ic_share
import kit_cmp.composeapp.generated.resources.ic_star_empty
import kit_cmp.composeapp.generated.resources.ic_star_filled
import kit_cmp.composeapp.generated.resources.showcase_kit_app_bar_demo_title
import kit_cmp.composeapp.generated.resources.showcase_kit_app_bar_description
import kit_cmp.composeapp.generated.resources.showcase_kit_app_bar_icon_description
import kit_cmp.composeapp.generated.resources.showcase_kit_app_bar_icon_title
import kit_cmp.composeapp.generated.resources.showcase_kit_app_bar_title
import kit_cmp.composeapp.generated.resources.showcase_kit_banner_ad_placeholder_description
import kit_cmp.composeapp.generated.resources.showcase_kit_banner_ad_placeholder_title
import kit_cmp.composeapp.generated.resources.showcase_kit_button_group_description
import kit_cmp.composeapp.generated.resources.showcase_kit_button_group_material_icons_error
import kit_cmp.composeapp.generated.resources.showcase_kit_button_group_option1
import kit_cmp.composeapp.generated.resources.showcase_kit_button_group_option2
import kit_cmp.composeapp.generated.resources.showcase_kit_button_group_option3
import kit_cmp.composeapp.generated.resources.showcase_kit_button_group_title
import kit_cmp.composeapp.generated.resources.showcase_kit_empty_data_view_card_description
import kit_cmp.composeapp.generated.resources.showcase_kit_empty_data_view_card_no_data
import kit_cmp.composeapp.generated.resources.showcase_kit_empty_data_view_card_title
import kit_cmp.composeapp.generated.resources.showcase_kit_empty_results_message_description
import kit_cmp.composeapp.generated.resources.showcase_kit_empty_results_message_title
import kit_cmp.composeapp.generated.resources.showcase_kit_favorite_button_description
import kit_cmp.composeapp.generated.resources.showcase_kit_favorite_button_title
import kit_cmp.composeapp.generated.resources.showcase_kit_filter_button_description
import kit_cmp.composeapp.generated.resources.showcase_kit_filter_button_filter
import kit_cmp.composeapp.generated.resources.showcase_kit_filter_button_sort
import kit_cmp.composeapp.generated.resources.showcase_kit_filter_button_title
import kit_cmp.composeapp.generated.resources.showcase_kit_footer_card_browse
import kit_cmp.composeapp.generated.resources.showcase_kit_footer_card_browse_catalog
import kit_cmp.composeapp.generated.resources.showcase_kit_footer_card_description
import kit_cmp.composeapp.generated.resources.showcase_kit_footer_card_search
import kit_cmp.composeapp.generated.resources.showcase_kit_footer_card_title
import kit_cmp.composeapp.generated.resources.showcase_kit_footer_card_want_more
import kit_cmp.composeapp.generated.resources.showcase_kit_horizontal_progress_bar_description
import kit_cmp.composeapp.generated.resources.showcase_kit_horizontal_progress_bar_title
import kit_cmp.composeapp.generated.resources.showcase_kit_icon_button_description
import kit_cmp.composeapp.generated.resources.showcase_kit_icon_button_title
import kit_cmp.composeapp.generated.resources.showcase_kit_info_label_beta
import kit_cmp.composeapp.generated.resources.showcase_kit_info_label_description
import kit_cmp.composeapp.generated.resources.showcase_kit_info_label_hot
import kit_cmp.composeapp.generated.resources.showcase_kit_info_label_new
import kit_cmp.composeapp.generated.resources.showcase_kit_info_label_title
import kit_cmp.composeapp.generated.resources.showcase_kit_key_value_chip_build_caption
import kit_cmp.composeapp.generated.resources.showcase_kit_key_value_chip_build_value
import kit_cmp.composeapp.generated.resources.showcase_kit_key_value_chip_description
import kit_cmp.composeapp.generated.resources.showcase_kit_key_value_chip_status_caption
import kit_cmp.composeapp.generated.resources.showcase_kit_key_value_chip_status_value
import kit_cmp.composeapp.generated.resources.showcase_kit_key_value_chip_title
import kit_cmp.composeapp.generated.resources.showcase_kit_key_value_chip_version_caption
import kit_cmp.composeapp.generated.resources.showcase_kit_key_value_chip_version_value
import kit_cmp.composeapp.generated.resources.showcase_kit_label_description
import kit_cmp.composeapp.generated.resources.showcase_kit_label_large
import kit_cmp.composeapp.generated.resources.showcase_kit_label_small
import kit_cmp.composeapp.generated.resources.showcase_kit_label_title
import kit_cmp.composeapp.generated.resources.showcase_kit_label_with_icon
import kit_cmp.composeapp.generated.resources.showcase_kit_network_image_description
import kit_cmp.composeapp.generated.resources.showcase_kit_network_image_title
import kit_cmp.composeapp.generated.resources.showcase_kit_pulsating_circle_description
import kit_cmp.composeapp.generated.resources.showcase_kit_pulsating_circle_title
import kit_cmp.composeapp.generated.resources.showcase_kit_section_container_description
import kit_cmp.composeapp.generated.resources.showcase_kit_section_container_simple_section
import kit_cmp.composeapp.generated.resources.showcase_kit_section_container_simple_section_content
import kit_cmp.composeapp.generated.resources.showcase_kit_section_container_title
import kit_cmp.composeapp.generated.resources.showcase_kit_section_container_with_action
import kit_cmp.composeapp.generated.resources.showcase_kit_section_container_with_action_content
import kit_cmp.composeapp.generated.resources.showcase_kit_section_container_with_action_view_all
import kit_cmp.composeapp.generated.resources.showcase_kit_skeleton_item_description
import kit_cmp.composeapp.generated.resources.showcase_kit_skeleton_item_title
import kit_cmp.composeapp.generated.resources.showcase_kit_text_input_description
import kit_cmp.composeapp.generated.resources.showcase_kit_text_input_placeholder
import kit_cmp.composeapp.generated.resources.showcase_kit_text_input_sample_text
import kit_cmp.composeapp.generated.resources.showcase_kit_text_input_title
import kit_cmp.composeapp.generated.resources.showcase_kit_text_input_with_icons
import kit_cmp.composeapp.generated.resources.showcase_kit_text_with_icon_description
import kit_cmp.composeapp.generated.resources.showcase_kit_text_with_icon_end
import kit_cmp.composeapp.generated.resources.showcase_kit_text_with_icon_start
import kit_cmp.composeapp.generated.resources.showcase_kit_text_with_icon_title
import kit_cmp.composeapp.generated.resources.showcase_kit_vertical_progress_bar_description
import kit_cmp.composeapp.generated.resources.showcase_kit_vertical_progress_bar_title
import kit_cmp.composeapp.generated.resources.showcase_search_catalog_button_row_description
import kit_cmp.composeapp.generated.resources.showcase_search_catalog_button_row_title
import kit_cmp.composeapp.generated.resources.showcase_subtitle
import kit_cmp.composeapp.generated.resources.showcase_title
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowcaseScreen(
    state: ShowcaseContract.State,
    onEvent: (ShowcaseContract.Event) -> Unit
) {
    Scaffold(
        topBar = {
            KitAppBar(
                title = stringResource(Res.string.showcase_title),
                onBack = { onEvent(ShowcaseContract.Event.OnBack) },
                logo = Res.drawable.compose_multiplatform.toImageSource()
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(spacing.large)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(spacing.large)
        ) {
            Text(
                text = stringResource(Res.string.showcase_subtitle),
                style = typography.bold24,
                color = colors.onSurface
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = spacing.medium),
                color = colors.outline
            )

            // KitInfoLabel Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_info_label_title),
                description = stringResource(Res.string.showcase_kit_info_label_description)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.small),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    KitInfoLabel(text = stringResource(Res.string.showcase_kit_info_label_new))
                    KitInfoLabel(
                        text = stringResource(Res.string.showcase_kit_info_label_beta),
                        backgroundColor = colors.secondary,
                        textColor = colors.onSecondary
                    )
                    KitInfoLabel(
                        text = stringResource(Res.string.showcase_kit_info_label_hot),
                        backgroundColor = colors.error,
                        textColor = colors.onError
                    )
                }
            }

            // KitKeyValueChip Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_key_value_chip_title),
                description = stringResource(Res.string.showcase_kit_key_value_chip_description)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.small)
                ) {
                    KitKeyValueChip(
                        caption = stringResource(Res.string.showcase_kit_key_value_chip_status_caption),
                        value = stringResource(Res.string.showcase_kit_key_value_chip_status_value)
                    )
                    KitKeyValueChip(
                        caption = stringResource(Res.string.showcase_kit_key_value_chip_version_caption),
                        value = stringResource(Res.string.showcase_kit_key_value_chip_version_value)
                    )
                    KitKeyValueChip(
                        caption = stringResource(Res.string.showcase_kit_key_value_chip_build_caption),
                        value = stringResource(Res.string.showcase_kit_key_value_chip_build_value)
                    )
                }
            }

            // KitIconButton Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_icon_button_title),
                description = stringResource(Res.string.showcase_kit_icon_button_description)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    KitIconButton(
                        icon = Res.drawable.ic_star_empty,
                        onClickAction = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitIconButton - Favorite"))
                        },
                        tint = colors.error
                    )
                    KitIconButton(
                        icon = Res.drawable.ic_star_filled,
                        onClickAction = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitIconButton - Star"))
                        },
                        tint = colors.primary
                    )
                    KitIconButton(
                        icon = Res.drawable.ic_info,
                        onClickAction = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitIconButton - Info"))
                        },
                        tint = colors.secondary
                    )
                }
            }

            // KitSkeletonItem Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_skeleton_item_title),
                description = stringResource(Res.string.showcase_kit_skeleton_item_description)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.small)
                ) {
                    val skeletonColor by animatedSkeletonColor()
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(spacing.small),
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
                            Spacer(modifier = Modifier.height(spacing.xsmall))
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
                        shape = shapes.large,
                        color = { skeletonColor }
                    )
                }
            }

            // KitAppBarIcon Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_app_bar_icon_title),
                description = stringResource(Res.string.showcase_kit_app_bar_icon_description)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    KitAppBarIcon(
                        icon = Res.drawable.ic_info,
                        onClickAction = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitAppBarIcon - Menu"))
                        },
                        tint = colors.primary
                    )
                    KitAppBarIcon(
                        icon = Res.drawable.ic_settings,
                        onClickAction = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitAppBarIcon - Settings"))
                        },
                        tint = colors.secondary
                    )
                    KitAppBarIcon(
                        icon = Res.drawable.ic_share,
                        onClickAction = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitAppBarIcon - Share"))
                        },
                        tint = colors.tertiary
                    )
                }
            }

            // KitNetworkImage Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_network_image_title),
                description = stringResource(Res.string.showcase_kit_network_image_description)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    KitNetworkImage(
                        imageSource = "https://picsum.photos/100/100?random=1".toImageSource(),
                        modifier = Modifier.height(80.dp).width(80.dp),
                        shape = shapes.default
                    )
                    KitNetworkImage(
                        imageSource = "https://picsum.photos/100/100?random=2".toImageSource(),
                        modifier = Modifier.height(80.dp).width(80.dp),
                        shape = CircleShape
                    )
                    KitNetworkImage(
                        imageSource = "https://picsum.photos/100/100?random=3".toImageSource(),
                        modifier = Modifier.height(80.dp).width(80.dp),
                        shape = shapes.large
                    )
                }
            }

            // KitAppBar Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_app_bar_title),
                description = stringResource(Res.string.showcase_kit_app_bar_description)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = colors.surface
                    )
                ) {
                    KitAppBar(
                        title = stringResource(Res.string.showcase_kit_app_bar_demo_title),
                        onBack = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitAppBar - Navigation"))
                        },
                        actions = {
                            KitIconButton(
                                icon = Res.drawable.ic_back,
                                onClickAction = {
                                    onEvent(ShowcaseContract.Event.OnDemoButtonClick("KitAppBar - Home Action"))
                                },
                                tint = colors.onPrimary
                            )
                        }
                    )
                }
            }

            // KitFavoriteButton Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_favorite_button_title),
                description = stringResource(Res.string.showcase_kit_favorite_button_description)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.medium),
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
                title = stringResource(Res.string.showcase_kit_label_title),
                description = stringResource(Res.string.showcase_kit_label_description)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.small)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(spacing.small),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        KitLabel(
                            text = stringResource(Res.string.showcase_kit_label_small),
                            size = LabelSize.SMALL
                        )
                        KitLabel(
                            text = stringResource(Res.string.showcase_kit_label_large),
                            size = LabelSize.LARGE
                        )
                        KitLabel(
                            text = stringResource(Res.string.showcase_kit_label_with_icon),
                            iconStart = Res.drawable.ic_star_filled,
                            size = LabelSize.SMALL
                        )
                        KitLabel(
                            text = stringResource(Res.string.showcase_kit_label_with_icon),
                            iconEnd = Res.drawable.ic_star_filled,
                            size = LabelSize.SMALL
                        )
                        KitLabel(
                            text = stringResource(Res.string.showcase_kit_label_with_icon),
                            iconStart = Res.drawable.ic_star_filled,
                            iconEnd = Res.drawable.ic_star_filled,
                            size = LabelSize.SMALL
                        )
                    }
                }
            }

            // KitTextWithIcon Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_text_with_icon_title),
                description = stringResource(Res.string.showcase_kit_text_with_icon_description)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.small)
                ) {
                    KitTextWithIcon(
                        text = stringResource(Res.string.showcase_kit_text_with_icon_start),
                        icon = Res.drawable.ic_back,
                        iconGravity = IconGravity.START
                    )
                    KitTextWithIcon(
                        text = stringResource(Res.string.showcase_kit_text_with_icon_end),
                        icon = Res.drawable.ic_info,
                        iconGravity = IconGravity.END
                    )
                }
            }

            // KitBannerAdPlaceholder Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_banner_ad_placeholder_title),
                description = stringResource(Res.string.showcase_kit_banner_ad_placeholder_description)
            ) {
                KitBannerAdPlaceholder()
            }

            // KitPulsatingCircle Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_pulsating_circle_title),
                description = stringResource(Res.string.showcase_kit_pulsating_circle_description)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    KitPulsatingCircle(
                        modifier = Modifier.size(60.dp)
                    )
                    KitPulsatingCircle(
                        modifier = Modifier.size(40.dp),
                        color = colors.error.copy(alpha = 0.3f)
                    )
                }
            }

            // KitTextInput Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_text_input_title),
                description = stringResource(Res.string.showcase_kit_text_input_description)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.small)
                ) {
                    KitTextInput(
                        value = stringResource(Res.string.showcase_kit_text_input_sample_text),
                        onValueChange = {},
                        placeHolder = { Text(stringResource(Res.string.showcase_kit_text_input_placeholder)) }
                    )
                    KitTextInput(
                        value = "",
                        onValueChange = {},
                        placeHolder = { Text(stringResource(Res.string.showcase_kit_text_input_with_icons)) },
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
                title = stringResource(Res.string.showcase_kit_vertical_progress_bar_title),
                description = stringResource(Res.string.showcase_kit_vertical_progress_bar_description)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.medium),
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
                        color = colors.secondary
                    )
                    KitVerticalProgressBar(
                        progress = { 1.0f },
                        modifier = Modifier.height(100.dp),
                        width = 20.dp,
                        color = colors.error
                    )
                }
            }

            // KitHorizontalProgressBar Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_horizontal_progress_bar_title),
                description = stringResource(Res.string.showcase_kit_horizontal_progress_bar_description)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.small)
                ) {
                    KitHorizontalProgressBar(
                        progress = { 0.3f },
                        modifier = Modifier.fillMaxWidth()
                    )
                    KitHorizontalProgressBar(
                        progress = { 0.7f },
                        modifier = Modifier.fillMaxWidth(),
                        animate = true,
                        color = colors.secondary
                    )
                    KitHorizontalProgressBar(
                        progress = { 1.0f },
                        modifier = Modifier.fillMaxWidth(),
                        height = 20.dp,
                        color = colors.error
                    )
                }
            }

            // KitButtonGroup Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_button_group_title),
                description = stringResource(Res.string.showcase_kit_button_group_description)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.medium)
                ) {
                    KitButtonGroupRow {
                        KitGroupButton(
                            text = stringResource(Res.string.showcase_kit_button_group_option1),
                            selected = true,
                            size = KitGroupButtonSize.Small,
                            onClick = {
                                onEvent(ShowcaseContract.Event.OnDemoButtonClick("ButtonGroup - Option 1"))
                            }
                        )
                        KitGroupButton(
                            text = stringResource(Res.string.showcase_kit_button_group_option2),
                            selected = false,
                            size = KitGroupButtonSize.Small,
                            onClick = {
                                onEvent(ShowcaseContract.Event.OnDemoButtonClick("ButtonGroup - Option 2"))
                            }
                        )
                        KitGroupButton(
                            text = stringResource(Res.string.showcase_kit_button_group_option3),
                            selected = false,
                            size = KitGroupButtonSize.Small,
                            onClick = {
                                onEvent(ShowcaseContract.Event.OnDemoButtonClick("ButtonGroup - Option 3"))
                            }
                        )
                    }

                    Text(stringResource(Res.string.showcase_kit_button_group_material_icons_error))
                }
            }

            // KitFilterButton Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_filter_button_title),
                description = stringResource(Res.string.showcase_kit_filter_button_description)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.small)
                ) {
                    KitFilterButton(
                        text = stringResource(Res.string.showcase_kit_filter_button_filter),
                        onClick = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("FilterButton - Filter"))
                        }
                    )
                    KitFilterButton(
                        text = stringResource(Res.string.showcase_kit_filter_button_sort),
                        onClick = {
                            onEvent(ShowcaseContract.Event.OnDemoButtonClick("FilterButton - Sort"))
                        }
                    )
                }
            }

            // KitEmptyResultsMessage Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_empty_results_message_title),
                description = stringResource(Res.string.showcase_kit_empty_results_message_description)
            ) {
                KitEmptyResultsMessage(
                    icon = Res.drawable.ic_star_empty,
                    iconTint = colors.primary
                )
            }

            // KitEmptyDataViewCard Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_empty_data_view_card_title),
                description = stringResource(Res.string.showcase_kit_empty_data_view_card_description)
            ) {
                KitEmptyDataViewCard(
                    text = stringResource(Res.string.showcase_kit_empty_data_view_card_no_data),
                    icon = Res.drawable.ic_info,
                    onTryAgainClick = {
                        onEvent(ShowcaseContract.Event.OnDemoButtonClick("EmptyDataView - Try Again"))
                    }
                )
            }

            // KitSectionContainer Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_section_container_title),
                description = stringResource(Res.string.showcase_kit_section_container_description)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.medium)
                ) {
                    KitSectionContainer(
                        title = stringResource(Res.string.showcase_kit_section_container_simple_section),
                        content = {
                            Text(stringResource(Res.string.showcase_kit_section_container_simple_section_content))
                        }
                    )

                    KitSectionContainer(
                        title = stringResource(Res.string.showcase_kit_section_container_with_action),
                        action = {
                            Text(
                                stringResource(Res.string.showcase_kit_section_container_with_action_view_all),
                                color = colors.primary,
                                style = MaterialTheme.typography.labelMedium
                            )
                        },
                        content = {
                            Text(stringResource(Res.string.showcase_kit_section_container_with_action_content))
                        }
                    )
                }
            }

            // SearchCatalogButtonRow Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_search_catalog_button_row_title),
                description = stringResource(Res.string.showcase_search_catalog_button_row_description)
            ) {
                SearchCatalogButtonRow(
                    onClick = {
                        onEvent(ShowcaseContract.Event.OnDemoButtonClick("SearchCatalogButtonRow - Search"))
                    }
                )
            }

            // KitFooterCard Demo
            ComponentDemoCard(
                title = stringResource(Res.string.showcase_kit_footer_card_title),
                description = stringResource(Res.string.showcase_kit_footer_card_description)
            ) {
                KitFooterCard(
                    title = stringResource(Res.string.showcase_kit_footer_card_want_more),
                    subtitle = stringResource(Res.string.showcase_kit_footer_card_browse_catalog),
                    primaryActionText = stringResource(Res.string.showcase_kit_footer_card_browse),
                    backgroundImageUrl = "https://static.vecteezy.com/system/resources/thumbnails/036/324/708/small/ai-generated-picture-of-a-tiger-walking-in-the-forest-photo.jpg",
                    primaryActionIcon = Res.drawable.ic_info,
                    onPrimaryAction = {
                        onEvent(ShowcaseContract.Event.OnDemoButtonClick("FooterCard - Browse"))
                    },
                    secondaryActionIcon = Res.drawable.ic_settings,
                    secondaryActionText = stringResource(Res.string.showcase_kit_footer_card_search),
                    onSecondaryActionText = {
                        onEvent(ShowcaseContract.Event.OnDemoButtonClick("FooterCard - Search"))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
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
            containerColor = colors.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(spacing.large)
        ) {
            Text(
                text = title,
                style = typography.bold16,
                color = colors.onSurface
            )
            Text(
                text = description,
                style = typography.regular12,
                color = colors.onSurfaceVariant,
                modifier = Modifier.padding(bottom = spacing.medium)
            )
            content()
        }
    }
}