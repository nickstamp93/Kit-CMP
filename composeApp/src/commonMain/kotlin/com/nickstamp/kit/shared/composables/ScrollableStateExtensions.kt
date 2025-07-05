package com.nickstamp.kit.shared.composables

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun LazyGridState.isScrollingUp(): Boolean {

    var previousIndex by remember(this) { mutableIntStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableIntStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}


@Composable
fun LazyListState.isScrollingUp(): Boolean {

    var previousIndex by remember(this) { mutableIntStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableIntStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

val ScrollableState.firstVisibleItemIndex
    get() = when (this) {
        is LazyListState -> firstVisibleItemIndex
        is LazyGridState -> firstVisibleItemIndex
        else -> 0
    }

val ScrollableState.firstVisibleItemScrollOffset
    get() = when (this) {
        is LazyListState -> firstVisibleItemScrollOffset
        is LazyGridState -> firstVisibleItemScrollOffset
        else -> 0
    }

suspend fun ScrollableState.scrollToPosition(pos: Int) {
    if (this is LazyListState) {
        scrollToItem(pos.coerceAtLeast(0))
    } else if (this is LazyGridState) {
        scrollToItem(pos.coerceAtLeast(0))
    }
}

suspend fun ScrollableState.scrollToFirst() {
    if (this is LazyListState) {
        scrollToItem(0)
    } else if (this is LazyGridState) {
        scrollToItem(0)
    }
}

suspend fun ScrollableState.scrollToLast() {
    if (this is LazyListState) {
        scrollToItem((layoutInfo.totalItemsCount - 1).coerceAtLeast(0))
    } else if (this is LazyGridState) {
        scrollToItem((layoutInfo.totalItemsCount - 1).coerceAtLeast(0))
    }
}