package com.nickstamp.kit.presentation.shared.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun KitCollapsingLayout(
    collapsingContent: @Composable BoxScope.() -> Unit,
    content: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
    onCollapseStateChange: (Boolean) -> Unit = {},
    collapseNotifyPercentThreshold: Float = 20f
) {
    val localDensity = LocalDensity.current

    // States for heights and offsets
    var collapsingTopHeight by remember { mutableFloatStateOf(0f) }
    var offset by rememberSaveable { mutableFloatStateOf(0f) }
    var collapsingTopHeightDp by remember { mutableStateOf(0.dp) }
    // Track the previous state to detect changes
    var previousCollapsedState by remember { mutableStateOf(false) }

    fun checkAndNotifyIfCollapsed(newOffset: Float) {
        // Determine and notify the collapsed state
        val isCollapsed =
            newOffset <= -collapseNotifyPercentThreshold * collapsingTopHeight / 100 // notify as collapsed when 20% of header is collapsed
        if (isCollapsed != previousCollapsedState) {
            onCollapseStateChange(isCollapsed)
            previousCollapsedState = isCollapsed
        }
    }

    fun calculateOffset(delta: Float): Offset {
        val oldOffset = offset
        val newOffset = (oldOffset + delta).coerceIn(-collapsingTopHeight, 0f)
        offset = newOffset
        checkAndNotifyIfCollapsed(newOffset)
        return Offset(0f, newOffset - oldOffset)
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset =
                when {
                    available.y >= 0 -> Offset.Zero
                    offset == -collapsingTopHeight -> Offset.Zero
                    else -> calculateOffset(available.y)
                }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource,
            ): Offset =
                when {
                    available.y <= 0 -> Offset.Zero
                    offset == 0f -> Offset.Zero
                    else -> calculateOffset(available.y)
                }
        }
    }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection),
    ) {
        Box(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .onGloballyPositioned { coordinates ->
                    collapsingTopHeight = coordinates.size.height.toFloat()
                    collapsingTopHeightDp = with(localDensity) { coordinates.size.height.toDp() }
                }
                .offset { IntOffset(x = 0, y = offset.roundToInt()) },
            content = collapsingContent,
        )
        Box(
            modifier = Modifier.offset {
                IntOffset(
                    x = 0,
                    y = (collapsingTopHeight + offset).roundToInt()
                )
            },
            content = content,
        )
    }
}
