package com.nickstamp.kit.shared.utils

data class ToastInfo(
    val message: String,
    val duration: ToastDuration = ToastDuration.SHORT
)

enum class ToastDuration {
    SHORT, LONG
}

interface EffectHandler {
    fun showToast(toastInfo: ToastInfo)
    fun showSnackbar(message: String)
}

class DefaultEffectHandler : EffectHandler {
    override fun showToast(toastInfo: ToastInfo) {
        // Platform-specific toast implementation will be handled
        println("Toast: ${toastInfo.message}")
    }

    override fun showSnackbar(message: String) {
        // Platform-specific snackbar implementation will be handled
        println("Snackbar: $message")
    }
}