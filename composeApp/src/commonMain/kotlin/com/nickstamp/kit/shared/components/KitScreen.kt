package com.nickstamp.kit.shared.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nickstamp.kit.presentation.theme.AppTheme

@Composable
fun KitScreen(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    toolbar: @Composable () -> Unit = {
        KitAppBar(
            title = "Kit CMP"
        )
    },
    content: @Composable () -> Unit,
) {
    AppTheme(
        useDarkTheme = useDarkTheme
    ) {
        Scaffold(
            topBar = {
                toolbar()
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                content()
            }
        }
    }
}