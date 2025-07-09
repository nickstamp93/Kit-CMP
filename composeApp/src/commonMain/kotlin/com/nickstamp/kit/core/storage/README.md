# Storage Layer

This directory contains the storage layer implementation for the Kit-CMP project, providing a clean abstraction over DataStore for data persistence.

## Components

### DatastoreManager
The main storage interface providing type-safe operations for all primitive types and collections.

### UserPreferences
Example implementation showing how to create domain-specific preference managers.

### DataStoreFactory
Platform-specific DataStore creation and configuration.

## Usage Examples

### Basic Operations

```kotlin
class MyViewModel(private val datastoreManager: DatastoreManager) : BaseViewModel<...> {
    
    // Save and retrieve simple values
    private suspend fun saveUserSettings() {
        datastoreManager.saveString("username", "john_doe")
        datastoreManager.saveBoolean("notifications_enabled", true)
        datastoreManager.saveInt("app_theme", 1)
    }
    
    private suspend fun loadUserSettings() {
        val username = datastoreManager.getString("username", "")
        val notificationsEnabled = datastoreManager.getBoolean("notifications_enabled", true)
        val theme = datastoreManager.getInt("app_theme", 0)
    }
}
```

### Using Flow for Reactive Updates

```kotlin
class SettingsViewModel(private val datastoreManager: DatastoreManager) : BaseViewModel<...> {
    
    // Observe changes reactively
    val themeMode = datastoreManager.getStringFlow("theme_mode", "system")
    val notificationsEnabled = datastoreManager.getBooleanFlow("notifications_enabled", true)
    
    // Combine multiple preferences
    val userSettings = combine(
        themeMode,
        notificationsEnabled,
        datastoreManager.getStringFlow("language", "en")
    ) { theme, notifications, language ->
        UserSettings(theme, notifications, language)
    }
}
```

### Domain-Specific Preferences

```kotlin
// Create domain-specific preference managers
class UserPreferences(private val datastoreManager: DatastoreManager) {
    
    suspend fun saveUserSession(userId: String, name: String, email: String) {
        datastoreManager.saveString("user_id", userId)
        datastoreManager.saveString("user_name", name)
        datastoreManager.saveString("user_email", email)
        datastoreManager.saveBoolean("is_logged_in", true)
    }
    
    fun isLoggedInFlow(): Flow<Boolean> {
        return datastoreManager.getBooleanFlow("is_logged_in", false)
    }
    
    suspend fun logout() {
        datastoreManager.remove("user_id")
        datastoreManager.remove("user_name")
        datastoreManager.remove("user_email")
        datastoreManager.saveBoolean("is_logged_in", false)
    }
}
```

### Complete Example: Theme Manager

```kotlin
class ThemeManager(private val datastoreManager: DatastoreManager) {
    
    companion object {
        private const val KEY_THEME_MODE = "theme_mode"
        private const val KEY_DYNAMIC_COLORS = "dynamic_colors"
        
        const val THEME_LIGHT = "light"
        const val THEME_DARK = "dark"
        const val THEME_SYSTEM = "system"
    }
    
    suspend fun setThemeMode(mode: String) {
        datastoreManager.saveString(KEY_THEME_MODE, mode)
    }
    
    fun getThemeModeFlow(): Flow<String> {
        return datastoreManager.getStringFlow(KEY_THEME_MODE, THEME_SYSTEM)
    }
    
    suspend fun setDynamicColors(enabled: Boolean) {
        datastoreManager.saveBoolean(KEY_DYNAMIC_COLORS, enabled)
    }
    
    fun getDynamicColorsFlow(): Flow<Boolean> {
        return datastoreManager.getBooleanFlow(KEY_DYNAMIC_COLORS, false)
    }
    
    data class ThemeSettings(
        val mode: String,
        val dynamicColors: Boolean
    )
    
    fun getThemeSettingsFlow(): Flow<ThemeSettings> {
        return combine(
            getThemeModeFlow(),
            getDynamicColorsFlow()
        ) { mode, dynamicColors ->
            ThemeSettings(mode, dynamicColors)
        }
    }
}
```

### In ViewModel Usage

```kotlin
class MainViewModel(
    private val userPreferences: UserPreferences,
    private val themeManager: ThemeManager
) : BaseViewModel<MainContract.Event, MainContract.Effect, MainContract.State>(
    initialState = MainContract.State()
) {
    
    init {
        observeUserSession()
        observeThemeSettings()
    }
    
    private fun observeUserSession() {
        launchInViewModelScope {
            userPreferences.isLoggedInFlow().collect { isLoggedIn ->
                setState { copy(isLoggedIn = isLoggedIn) }
            }
        }
    }
    
    private fun observeThemeSettings() {
        launchInViewModelScope {
            themeManager.getThemeSettingsFlow().collect { themeSettings ->
                setState { copy(themeSettings = themeSettings) }
            }
        }
    }
    
    override fun onEvent(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.Logout -> logout()
            is MainContract.Event.ChangeTheme -> changeTheme(event.theme)
        }
    }
    
    private fun logout() {
        launchInViewModelScope {
            userPreferences.logout()
            setEffect(MainContract.Effect.NavigateToLogin)
        }
    }
    
    private fun changeTheme(theme: String) {
        launchInViewModelScope {
            themeManager.setThemeMode(theme)
        }
    }
}
```

## Supported Data Types

### Primitive Types
- **String**: `saveString()`, `getString()`, `getStringFlow()`
- **Int**: `saveInt()`, `getInt()`, `getIntFlow()`
- **Boolean**: `saveBoolean()`, `getBoolean()`, `getBooleanFlow()`
- **Long**: `saveLong()`, `getLong()`, `getLongFlow()`
- **Float**: `saveFloat()`, `getFloat()`, `getFloatFlow()`
- **Double**: `saveDouble()`, `getDouble()`, `getDoubleFlow()`

### Collections
- **String Set**: `saveStringSet()`, `getStringSet()`, `getStringSetFlow()`

### Utility Operations
- **Remove**: `remove(key)` - Remove specific key
- **Clear**: `clear()` - Remove all data
- **Contains**: `contains(key)` - Check if key exists
- **All Preferences**: `getAllPreferences()` - Get all preferences as Flow

## Features

- **Type Safety**: All operations are type-safe with proper defaults
- **Reactive**: All data can be observed as Flow for real-time updates
- **Multiplatform**: Works seamlessly on Android and iOS
- **Clean API**: Simple, intuitive interface for all operations
- **Error Handling**: Built-in error handling and default values
- **Performance**: Efficient DataStore implementation underneath
- **Dependency Injection**: Fully integrated with Koin DI

## DI Setup

The storage layer provides the interfaces and implementations. DataStore setup should be done in platform-specific code:

```kotlin
val storageModule = module {
    // Note: DataStore setup should be done in platform-specific code
    // Example setup when DataStore is available:
    // single { createDataStore(androidContext()) } // Android
    // single { createDataStore() } // iOS
    // single<DatastoreManager> { DatastoreManagerImpl(get()) }
    // single { UserPreferences(get()) }
}
```

### Platform-Specific DataStore Setup

#### Android
```kotlin
// In Android module
val androidStorageModule = module {
    single { 
        preferencesDataStore(name = "app_preferences")
            .create(androidContext())
    }
    single<DatastoreManager> { DatastoreManagerImpl(get()) }
    single { UserPreferences(get()) }
}
```

#### iOS
```kotlin
// In iOS module
val iosStorageModule = module {
    single {
        PreferenceDataStoreFactory.createWithPath(
            produceFile = { "app_preferences.preferences_pb" }
        )
    }
    single<DatastoreManager> { DatastoreManagerImpl(get()) }
    single { UserPreferences(get()) }
}
```

## Best Practices

1. **Create Domain-Specific Managers**: Don't use DatastoreManager directly in ViewModels
2. **Use Flows for Reactive UI**: Observe data changes with flows
3. **Provide Default Values**: Always provide sensible defaults
4. **Group Related Settings**: Create specific managers for related preferences
5. **Consistent Key Naming**: Use consistent, descriptive key names
6. **Bulk Operations**: Group related saves together when possible

## Example Integration in Feature Module

```kotlin
// In your feature DI module
val featureModule = module {
    single { ThemeManager(get()) }
    single { NotificationManager(get()) }
    factory { SettingsViewModel(get(), get(), get()) }
}
```

The storage layer provides a robust, type-safe foundation for all your app's persistence needs! 🚀