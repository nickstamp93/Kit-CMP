package com.nickstamp.kit.core.helpers

interface SystemHelper {

    fun getCurrentVersion(): Int
    fun getCurrentVersionName(): String
    fun getAppStoreUrl(): String
    fun isAppleEnvironment(): Boolean
}