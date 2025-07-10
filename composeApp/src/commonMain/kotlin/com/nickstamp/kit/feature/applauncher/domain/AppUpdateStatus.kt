package com.nickstamp.kit.feature.applauncher.domain

import androidx.compose.runtime.Immutable
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.text_update_mandatory
import kit_cmp.composeapp.generated.resources.text_update_mandatory_message
import kit_cmp.composeapp.generated.resources.text_update_optional
import kit_cmp.composeapp.generated.resources.text_update_optional_message
import org.jetbrains.compose.resources.StringResource

@Immutable
sealed class AppUpdateStatus {
    data object UpToDate : AppUpdateStatus()
    data class UpdateAvailable(
        val type: AppUpdateType,
        val store: AppUpdateStore,
    ) : AppUpdateStatus() {
        val isMandatoryUpdate = (type == AppUpdateType.MANDATORY)
    }
}

enum class AppUpdateType(
    val text: StringResource,
    val message: StringResource,
) {
    MANDATORY(
        text = Res.string.text_update_mandatory,
        message = Res.string.text_update_mandatory_message
    ),
    OPTIONAL(
        text = Res.string.text_update_optional,
        message = Res.string.text_update_optional_message
    )
}

@Immutable
sealed class AppUpdateStore(val rawUrl: String) {
    data class Google(val url: String) : AppUpdateStore(url)
    data class Apple(val url: String) : AppUpdateStore(url)
    data class CDN(val url: String) : AppUpdateStore(url)
    data object Unknown : AppUpdateStore("")
}