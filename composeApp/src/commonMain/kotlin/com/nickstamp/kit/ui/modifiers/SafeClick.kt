package com.nickstamp.kit.ui.modifiers

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

private const val DEFAULT_CLICK_DEBOUNCE_MS = 200L

@OptIn(ExperimentalTime::class)
@Composable
fun rememberSafeClick(
    debounceMs: Long = DEFAULT_CLICK_DEBOUNCE_MS,
    onClick: () -> Unit,
): () -> Unit {
    val lastClick = remember { mutableLongStateOf(0L) }
    val latestOnClick by rememberUpdatedState(onClick)
    return {

        val currentTime = Clock.System.now().toEpochMilliseconds()

        if (currentTime - lastClick.longValue > debounceMs) {
            lastClick.longValue = currentTime
            latestOnClick()
        }
    }
}

fun Modifier.safeClickable(
    interactionSource: MutableInteractionSource?,
    indication: Indication,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    debounceMs: Long = DEFAULT_CLICK_DEBOUNCE_MS,
    onClick: () -> Unit
): Modifier = composed {

    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        interactionSource = interactionSource,
        indication = indication,
        onClick = rememberSafeClick(
            debounceMs = debounceMs,
            onClick = onClick
        )
    )
}

fun Modifier.safeClickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    debounceMs: Long = DEFAULT_CLICK_DEBOUNCE_MS,
    onClick: () -> Unit
): Modifier = composed {

    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = rememberSafeClick(
            debounceMs = debounceMs,
            onClick = onClick
        )
    )
}