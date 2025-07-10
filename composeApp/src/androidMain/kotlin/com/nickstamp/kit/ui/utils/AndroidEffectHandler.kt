package com.nickstamp.kit.ui.utils

import android.content.Context
import android.widget.Toast
import com.nickstamp.kit.core.model.UiText
import com.nickstamp.kit.core.model.UiText.Companion.toUiText
import org.jetbrains.compose.resources.StringResource

class AndroidEffectHandler(private val context: Context) : EffectHandler {
    
    override fun showToast(toastInfo: ToastInfo) {
        val duration = when (toastInfo.duration) {
            ToastDuration.SHORT -> Toast.LENGTH_SHORT
            ToastDuration.LONG -> Toast.LENGTH_LONG
        }
        Toast.makeText(context, toastInfo.message, duration).show()
    }
    
    override fun showSnackbar(message: String) {
        // For now, fallback to toast for snackbar
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}