package com.nickstamp.kit.core.storage.example

import com.nickstamp.kit.core.storage.DatastoreManager
import kotlinx.coroutines.flow.Flow

class DefaultDatastoreManager(
    private val datastoreManager: DatastoreManager
) {
    companion object Companion {
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_THEME_MODE = "theme_mode"
        private const val KEY_LANGUAGE = "language"
        private const val KEY_NOTIFICATIONS_ENABLED = "notifications_enabled"
        private const val KEY_LAST_LOGIN_TIMESTAMP = "last_login_timestamp"
        private const val KEY_APP_VERSION = "app_version"
        private const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"
        private const val KEY_FAVORITE_CATEGORIES = "favorite_categories"
        
        // Default values
        private const val DEFAULT_THEME_MODE = "system"
        private const val DEFAULT_LANGUAGE = "en"
    }
    
    // User Session
    suspend fun saveUserId(userId: String) {
        datastoreManager.saveString(KEY_USER_ID, userId)
    }
    
    suspend fun getUserId(): String {
        return datastoreManager.getString(KEY_USER_ID)
    }
    
    fun getUserIdFlow(): Flow<String> {
        return datastoreManager.getStringFlow(KEY_USER_ID)
    }
    
    suspend fun saveUserName(name: String) {
        datastoreManager.saveString(KEY_USER_NAME, name)
    }
    
    suspend fun getUserName(): String {
        return datastoreManager.getString(KEY_USER_NAME)
    }
    
    fun getUserNameFlow(): Flow<String> {
        return datastoreManager.getStringFlow(KEY_USER_NAME)
    }
    
    suspend fun saveUserEmail(email: String) {
        datastoreManager.saveString(KEY_USER_EMAIL, email)
    }
    
    suspend fun getUserEmail(): String {
        return datastoreManager.getString(KEY_USER_EMAIL)
    }
    
    fun getUserEmailFlow(): Flow<String> {
        return datastoreManager.getStringFlow(KEY_USER_EMAIL)
    }
    
    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        datastoreManager.saveBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
        if (isLoggedIn) {
            saveLastLoginTimestamp(1234567890L) // Replace with actual timestamp
        }
    }
    
    suspend fun isLoggedIn(): Boolean {
        return datastoreManager.getBoolean(KEY_IS_LOGGED_IN, false)
    }
    
    fun isLoggedInFlow(): Flow<Boolean> {
        return datastoreManager.getBooleanFlow(KEY_IS_LOGGED_IN, false)
    }
    
    // App Settings
    suspend fun saveThemeMode(themeMode: String) {
        datastoreManager.saveString(KEY_THEME_MODE, themeMode)
    }
    
    suspend fun getThemeMode(): String {
        return datastoreManager.getString(KEY_THEME_MODE, DEFAULT_THEME_MODE)
    }
    
    fun getThemeModeFlow(): Flow<String> {
        return datastoreManager.getStringFlow(KEY_THEME_MODE, DEFAULT_THEME_MODE)
    }
    
    suspend fun saveLanguage(language: String) {
        datastoreManager.saveString(KEY_LANGUAGE, language)
    }
    
    suspend fun getLanguage(): String {
        return datastoreManager.getString(KEY_LANGUAGE, DEFAULT_LANGUAGE)
    }
    
    fun getLanguageFlow(): Flow<String> {
        return datastoreManager.getStringFlow(KEY_LANGUAGE, DEFAULT_LANGUAGE)
    }
    
    suspend fun setNotificationsEnabled(enabled: Boolean) {
        datastoreManager.saveBoolean(KEY_NOTIFICATIONS_ENABLED, enabled)
    }
    
    suspend fun areNotificationsEnabled(): Boolean {
        return datastoreManager.getBoolean(KEY_NOTIFICATIONS_ENABLED, true)
    }
    
    fun areNotificationsEnabledFlow(): Flow<Boolean> {
        return datastoreManager.getBooleanFlow(KEY_NOTIFICATIONS_ENABLED, true)
    }
    
    // App Metadata
    private suspend fun saveLastLoginTimestamp(timestamp: Long) {
        datastoreManager.saveLong(KEY_LAST_LOGIN_TIMESTAMP, timestamp)
    }
    
    suspend fun getLastLoginTimestamp(): Long {
        return datastoreManager.getLong(KEY_LAST_LOGIN_TIMESTAMP, 0L)
    }
    
    suspend fun saveAppVersion(version: String) {
        datastoreManager.saveString(KEY_APP_VERSION, version)
    }
    
    suspend fun getAppVersion(): String {
        return datastoreManager.getString(KEY_APP_VERSION)
    }
    
    suspend fun setOnboardingCompleted(completed: Boolean) {
        datastoreManager.saveBoolean(KEY_ONBOARDING_COMPLETED, completed)
    }
    
    suspend fun isOnboardingCompleted(): Boolean {
        return datastoreManager.getBoolean(KEY_ONBOARDING_COMPLETED, false)
    }
    
    fun isOnboardingCompletedFlow(): Flow<Boolean> {
        return datastoreManager.getBooleanFlow(KEY_ONBOARDING_COMPLETED, false)
    }
    
    // Collections
    suspend fun saveFavoriteCategories(categories: Set<String>) {
        datastoreManager.saveStringSet(KEY_FAVORITE_CATEGORIES, categories)
    }
    
    suspend fun getFavoriteCategories(): Set<String> {
        return datastoreManager.getStringSet(KEY_FAVORITE_CATEGORIES, emptySet())
    }
    
    fun getFavoriteCategoriesFlow(): Flow<Set<String>> {
        return datastoreManager.getStringSetFlow(KEY_FAVORITE_CATEGORIES, emptySet())
    }
    
    suspend fun addFavoriteCategory(category: String) {
        val currentFavorites = getFavoriteCategories()
        saveFavoriteCategories(currentFavorites + category)
    }
    
    suspend fun removeFavoriteCategory(category: String) {
        val currentFavorites = getFavoriteCategories()
        saveFavoriteCategories(currentFavorites - category)
    }
    
    // Bulk Operations
    suspend fun saveUserSession(userId: String, name: String, email: String) {
        saveUserId(userId)
        saveUserName(name)
        saveUserEmail(email)
        setLoggedIn(true)
    }
    
    suspend fun clearUserSession() {
        datastoreManager.remove(KEY_USER_ID)
        datastoreManager.remove(KEY_USER_NAME)
        datastoreManager.remove(KEY_USER_EMAIL)
        setLoggedIn(false)
    }
    
    suspend fun clearAllData() {
        datastoreManager.clear()
    }
    
    // Utility Methods
    suspend fun hasUserData(): Boolean {
        return datastoreManager.contains(KEY_USER_ID) && getUserId().isNotEmpty()
    }
}