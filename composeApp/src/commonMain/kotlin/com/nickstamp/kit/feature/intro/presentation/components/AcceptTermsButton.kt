package com.nickstamp.kit.feature.intro.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.nickstamp.kit.ui.theme.AppTheme.colors
import com.nickstamp.kit.ui.theme.AppTheme.typography
import com.nickstamp.kit.ui.theme.lowEmphasis
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.accept_terms_caps
import org.jetbrains.compose.resources.stringResource

@Composable
fun AcceptTermsButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        colors = ButtonColors(
            containerColor = colors.secondary,
            contentColor = Color.White,
            disabledContainerColor = colors.secondary.lowEmphasis(),
            disabledContentColor = Color.White.lowEmphasis()
        ),
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = stringResource(Res.string.accept_terms_caps),
            style = typography.bold16,
            textAlign = TextAlign.Center,
            modifier = Modifier
        )
    }
}