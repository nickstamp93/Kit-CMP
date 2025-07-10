package com.nickstamp.kit.ui.utils

data class ToastInfo(
    val message: String,
    val duration: ToastDuration = ToastDuration.SHORT
)

enum class ToastDuration {
    SHORT, LONG
}

interface EffectHandler {
    fun showToast(toastInfo: ToastInfo)
    fun closeApp()
}