package com.nickstamp.kit.core.helpers

interface SystemHelper {

    fun getCurrentVersion(): Int
    fun getAppStoreUrl(): String
    fun isAppleEnvironment(): Boolean
}