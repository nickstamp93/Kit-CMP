package com.nickstamp.kit.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import com.nickstamp.kit.ui.theme.AppTheme.colors
import com.nickstamp.kit.ui.theme.AppTheme.shapes
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import com.nickstamp.kit.ui.theme.AppTheme.typography
import com.nickstamp.kit.ui.theme.mediumEmphasis
import kotlinx.coroutines.delay

@Composable
fun KitAppBarSearchTextInput(
    placeHolder: String,
    searchQuery: () -> String,
    setSearchQuery: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    KitTextInput(
        value = searchQuery(),
        onValueChange = setSearchQuery,
        shape = shapes.rounded50,
        placeHolder = {
            Text(
                text = placeHolder,
                style = typography.regular14.copy(color = colors.onSurface.mediumEmphasis())
            )
        },
        debounce = 300,
        textStyle = typography.regular16.copy(color = colors.onSurface),
        contentPadding = PaddingValues(vertical = spacing.default, horizontal = spacing.large),
        inputColors = KitTextInputDefaults.surfaceColors(),
        modifier = modifier.focusRequester(focusRequester)
    )

    LaunchedEffect(searchQuery()) {
        if (searchQuery().isEmpty()) {
            delay(200)
            focusRequester.requestFocus()
        }

    }
}