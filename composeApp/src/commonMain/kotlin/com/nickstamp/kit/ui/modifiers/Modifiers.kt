package com.nickstamp.kit.ui.modifiers

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier

inline fun Modifier.ifCondition(
    condition: Boolean,
    modifier: Modifier.() -> Modifier,
): Modifier = if (condition) {
    then(modifier(Modifier))
} else {
    then(this)
}

inline fun Modifier.ifCondition(
    condition: Boolean,
    onTrue: Modifier.() -> Modifier,
    onFalse: Modifier.() -> Modifier,
): Modifier = if (condition) {
    then(onTrue(Modifier))
} else {
    then(onFalse(Modifier))
}

fun Modifier.clickableNoRipple(
    onClick: () -> Unit,
) = this.clickable(
    onClick = onClick,
    indication = null,
    interactionSource = null
)