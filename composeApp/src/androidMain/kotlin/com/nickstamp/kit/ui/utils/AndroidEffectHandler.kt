package com.nickstamp.kit.ui.utils

import android.content.Context
import android.widget.Toast
import kotlin.system.exitProcess

class AndroidEffectHandler(private val context: Context) : EffectHandler {

    override fun showToast(toastInfo: ToastInfo) {
        val duration = when (toastInfo.duration) {
            ToastDuration.SHORT -> Toast.LENGTH_SHORT
            ToastDuration.LONG -> Toast.LENGTH_LONG
        }
        Toast.makeText(context, toastInfo.message, duration).show()
    }

    override fun closeApp() {
        exitProcess(0)
    }
}