package com.nickstamp.kit.ui.utils

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertAction
import platform.UIKit.UIAlertActionStyleDefault
import platform.UIKit.UIAlertControllerStyleAlert
import platform.UIKit.UIApplication
import platform.UIKit.UIWindow

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
    
    @OptIn(ExperimentalForeignApi::class)
    override fun showSnackbar(message: String) {
        // For now, use same alert mechanism
        showToast(ToastInfo(message))
    }
}