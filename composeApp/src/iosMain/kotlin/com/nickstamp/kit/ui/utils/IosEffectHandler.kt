package com.nickstamp.kit.ui.utils

import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIAlertAction
import platform.UIKit.UIAlertActionStyleDefault
import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertControllerStyleAlert
import platform.UIKit.UIApplication
import platform.UIKit.UIWindow
import kotlin.system.exitProcess

class IosEffectHandler : EffectHandler {

    @OptIn(ExperimentalForeignApi::class)
    override fun showToast(toastInfo: ToastInfo) {
        val alert = UIAlertController.alertControllerWithTitle(
            title = null,
            message = toastInfo.message,
            preferredStyle = UIAlertControllerStyleAlert
        )

        val okAction = UIAlertAction.actionWithTitle(
            title = "OK",
            style = UIAlertActionStyleDefault,
            handler = null
        )

        alert.addAction(okAction)

        // Get the root view controller
        val window = UIApplication.sharedApplication.windows.firstOrNull() as? UIWindow
        window?.rootViewController?.presentViewController(alert, animated = true, completion = null)
    }

    override fun closeApp() {
        exitProcess(0)
    }
}