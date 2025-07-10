package com.nickstamp.kit.core.helpers

import android.content.Context
import android.content.pm.PackageManager

class AndroidSystemHelper(
    private val context: Context
) : SystemHelper {

    override fun getCurrentVersion(): Int {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            @Suppress("DEPRECATION")
            packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            1 // Default version
        }
    }

    override fun getAppStoreUrl(): String {
        return "https://play.google.com/store/apps/details?id=${context.packageName}"
    }

    override fun isAppleEnvironment(): Boolean {
        return false
    }
}