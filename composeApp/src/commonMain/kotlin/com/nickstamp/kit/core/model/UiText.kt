package com.nickstamp.kit.core.model

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.PluralStringResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource

sealed class UiText {
    data object Empty : UiText()
    data class DynamicString(val value: String) : UiText()
    data class Resource(
        val id: StringResource,
        val args: List<Any>
    ) : UiText() {
        constructor(id: StringResource, vararg args: Any) : this(id, args.toList())
    }

    data class PluralResource(
        val id: PluralStringResource,
        val value: Int
    ) : UiText()

    companion object {
        fun StringResource.toUiText() = Resource(this)
        fun String.toUiText() = DynamicString(this)
        fun Throwable.toUiText() = DynamicString(this.message ?: "Error")
    }
}

@Composable
fun UiText.asString(): String {
    return when (this) {
        is UiText.DynamicString -> value
        is UiText.Empty -> ""
        is UiText.PluralResource -> pluralStringResource(id, value)
        is UiText.Resource -> stringResource(id, *args.toTypedArray())
    }
}