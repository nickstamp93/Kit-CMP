package com.nickstamp.kit.core.helpers

import platform.Foundation.NSBundle

class IosSystemHelper : SystemHelper {

    override fun getCurrentVersion(): Int {
        return try {
            val version = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleVersion") as? String
            version?.toIntOrNull() ?: 1
        } catch (e: Exception) {
            1 // Default version
        }
    }

    override fun getAppStoreUrl(): String {
        val bundleId = NSBundle.mainBundle.bundleIdentifier ?: "unknown"
        return "https://apps.apple.com/app/id123456789" // Replace with actual App Store URL
    }

    override fun isAppleEnvironment(): Boolean {
        return true
    }
}