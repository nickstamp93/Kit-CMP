package com.nickstamp.kit.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.ui.theme.AppTheme.shapes
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import kotlinx.coroutines.delay


object KitTextInputDefaults {
    @Composable
    fun defaultColors(): TextFieldColors {
        return TextFieldDefaults.colors()
    }

    @Composable
    fun surfaceColors(): TextFieldColors {
        return TextFieldDefaults.colors().copy(
            focusedTextColor = colorScheme.onSurface,
            unfocusedTextColor = colorScheme.onSurface,
            disabledTextColor = colorScheme.onSurface,
            disabledContainerColor = colorScheme.surface,
            focusedContainerColor = colorScheme.surface,
            unfocusedContainerColor = colorScheme.surface,
            unfocusedPlaceholderColor = colorScheme.onSurface,
            focusedPlaceholderColor = colorScheme.onSurface,
            cursorColor = colorScheme.onSurface
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KitTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    colors: TextFieldColors = KitTextInputDefaults.defaultColors(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    maxLines: Int = 1,
    minLines: Int = 1,
    debounce: Long = 0L,
    shape: Shape = shapes.default,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = spacing.large, vertical = spacing.default
    ),
    textStyle: TextStyle = TextStyle.Default,
    label: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    placeHolder: @Composable (() -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }

    val innerColors = colors.copy(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )

    var textValue by remember(value) { mutableStateOf(value) }

    val singleLine = (maxLines == 1) && (minLines == 1)
    BasicTextField(
        value = textValue,
        onValueChange = {
            textValue = it
        },
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        textStyle = textStyle,
        cursorBrush = SolidColor(colorScheme.onSurface),
        modifier = modifier.widthIn(min = 40.dp),
        decorationBox = { innerTextField ->
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.xsmall),
                modifier = Modifier
                    .clip(shape)
                    .background(color = innerColors.focusedContainerColor)
                    .padding(contentPadding),
            ) {
                if (label != null) {
                    label()
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.default),
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (leadingIcon != null) {
                        leadingIcon()
                    }
                    if (prefix != null) {
                        prefix()
                    }

                    Row(
                        modifier = Modifier
                            .weight(1f, fill = false)
                            .padding(horizontal = spacing.small),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextFieldDefaults.DecorationBox(
                            value = textValue,
                            visualTransformation = visualTransformation,
                            innerTextField = innerTextField,
                            singleLine = singleLine,
                            enabled = enabled,
                            interactionSource = interactionSource,
                            placeholder = {
                                if (textValue.isEmpty() && placeHolder != null) {
                                    placeHolder()
                                }
                            },
                            leadingIcon = null, // Already handled in the parent Row
                            trailingIcon = null, // Already handled in the parent Row
                            prefix = null, // Already handled in the parent Row
                            suffix = null, // Already handled in the parent Row
                            colors = innerColors,
                            contentPadding = PaddingValues(0.dp) // Already handled in the parent Row
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }

                    if (suffix != null) {
                        suffix()
                    }
                    if (trailingIcon != null) {
                        trailingIcon()
                    }
                }
            }
        }
    )

    LaunchedEffect(textValue) {
        delay(debounce)
        onValueChange(textValue)
    }
}

