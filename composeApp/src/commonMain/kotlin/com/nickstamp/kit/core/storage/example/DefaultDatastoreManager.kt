package com.nickstamp.kit.core.storage.example

import com.nickstamp.kit.core.storage.DatastoreManager
import kotlinx.coroutines.flow.Flow

class DefaultDatastoreManager(
    private val datastoreManager: DatastoreManager
) {
    companion object Companion {
        // Theme and UI
        private const val KEY_IS_DARK_THEME = "is_dark_theme"
        private const val KEY_LANGUAGE = "language"
        
        // App State
        private const val KEY_INTRO_SEEN = "intro_seen"
        private const val KEY_APP_VERSION = "app_version"
        
        // Default values
        private const val DEFAULT_LANGUAGE = "en"
    }
    
    // Theme Settings
    suspend fun saveDarkTheme(isDarkTheme: Boolean) {
        datastoreManager.saveBoolean(KEY_IS_DARK_THEME, isDarkTheme)
    }
    
    suspend fun isDarkTheme(): Boolean {
        return datastoreManager.getBoolean(KEY_IS_DARK_THEME, false)
    }
    
    fun isDarkThemeFlow(): Flow<Boolean> {
        return datastoreManager.getBooleanFlow(KEY_IS_DARK_THEME, false)
    }
    
    // Language Settings
    suspend fun saveLanguage(language: String) {
        datastoreManager.saveString(KEY_LANGUAGE, language)
    }
    
    suspend fun getLanguage(): String {
        return datastoreManager.getString(KEY_LANGUAGE, DEFAULT_LANGUAGE)
    }
    
    fun getLanguageFlow(): Flow<String> {
        return datastoreManager.getStringFlow(KEY_LANGUAGE, DEFAULT_LANGUAGE)
    }
    
    // Intro/Onboarding
    suspend fun setIntroSeen(seen: Boolean) {
        datastoreManager.saveBoolean(KEY_INTRO_SEEN, seen)
    }
    
    suspend fun isIntroSeen(): Boolean {
        return datastoreManager.getBoolean(KEY_INTRO_SEEN, false)
    }
    
    fun isIntroSeenFlow(): Flow<Boolean> {
        return datastoreManager.getBooleanFlow(KEY_INTRO_SEEN, false)
    }
    
    // App Metadata
    suspend fun saveAppVersion(version: String) {
        datastoreManager.saveString(KEY_APP_VERSION, version)
    }
    
    suspend fun getAppVersion(): String {
        return datastoreManager.getString(KEY_APP_VERSION)
    }
    
    // Utility Methods
    suspend fun clearAllData() {
        datastoreManager.clear()
    }
}