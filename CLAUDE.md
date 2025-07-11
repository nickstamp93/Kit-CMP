# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview
This is a **Kotlin Multiplatform Framework** project designed to jumpstart new CMP projects. It targets Android and iOS platforms using Compose Multiplatform with a comprehensive set of commonly used dependencies and follows clean architecture principles with strict separation of concerns.

## Build Commands
- **Build project**: `./gradlew build`
- **Clean build**: `./gradlew clean build`
- **Run tests**: `./gradlew test`
- **Android debug build**: `./gradlew :composeApp:assembleDebug`
- **Android release build**: `./gradlew :composeApp:assembleRelease`
- **iOS framework**: `./gradlew :composeApp:linkReleaseFrameworkIosArm64`

## Testing
- **Run all tests**: `./gradlew test`
- **Run common tests**: `./gradlew :composeApp:testDebugUnitTest`
- Tests are located in `composeApp/src/commonTest/kotlin/`
- Uses kotlin-test framework

## Architecture & Project Structure

### Clean Architecture Principles
This framework follows **Clean Architecture** with strict separation of concerns:
- **Feature-based packages**: Code is organized by features, not by technical layers
- **Dependency Injection**: All helpers, mappers, repositories, and use cases are DI injected using Koin
- **MVI Pattern**: Each screen follows Model-View-Intent pattern with Contract, ViewModel, and Composable
- **Layered Architecture**: Clear separation between Presentation, Domain, and Data layers

### Complete Package Structure
```
composeApp/src/commonMain/kotlin/com/nickstamp/kit/
├── App.kt                   # Main app entry point with theme and navigation setup
├── Platform.kt              # Platform detection interface (expect/actual)
├── core/                    # Core framework components
│   ├── arch/               # Architecture base classes
│   │   └── BaseViewModel.kt # MVI base class for all ViewModels
│   ├── di/                 # Dependency injection modules
│   │   └── CoreModule.kt   # Core DI module (HTTP, DataStore, helpers)
│   ├── helpers/            # Cross-platform helper interfaces
│   │   ├── DateTimeHelper.kt # Date/time operations interface
│   │   ├── SystemHelper.kt   # System info interface (version, store URLs)
│   │   └── impl/            # Helper implementations
│   ├── model/              # Core domain models
│   │   └── UiText.kt       # Internationalization text wrapper
│   ├── network/            # Network layer
│   │   ├── ApiResult.kt    # Result wrapper with error handling
│   │   ├── ApiService.kt   # Generic HTTP client service
│   │   ├── EndpointBuilder.kt # URL building utility
│   │   ├── HttpClient.kt   # Ktor client configuration
│   │   ├── NetworkConfig.kt # Base URLs and network settings
│   │   └── SafeApiCall.kt  # Network call error handling
│   └── storage/            # Data persistence layer
│       ├── DataStoreFactory.kt # Platform-specific DataStore creation
│       ├── DatastoreManager.kt # Type-safe preferences interface
│       └── example/        # Example DataStore usage
├── di/                     # App-level dependency injection
│   ├── KoinConfig.kt       # Platform-specific Koin setup (expect/actual)
│   └── KoinModule.kt       # Main app DI module
├── feature/               # Feature modules (organized by business domain)
│   ├── applauncher/       # App startup, splash, update checking
│   │   ├── data/          # Empty (uses domain models directly)
│   │   ├── domain/        # App update status and logic
│   │   ├── helper/        # Platform-specific update helpers
│   │   ├── presentation/  # Launch screen UI and logic
│   │   └── di/           # AppLauncher DI module
│   ├── config/           # Remote configuration management
│   │   ├── data/         # Configuration API and caching
│   │   │   ├── datasource/ # Local/remote data sources
│   │   │   ├── dto/      # API response models
│   │   │   ├── endpoints/ # API endpoint definitions
│   │   │   ├── mapper/   # DTO to domain mapping
│   │   │   ├── repository/ # Repository implementation
│   │   │   └── service/  # Network service
│   │   ├── domain/       # Configuration business logic
│   │   │   ├── model/    # Domain models (Configuration, AppUpdate, etc.)
│   │   │   ├── repository/ # Repository interface
│   │   │   └── usecase/  # Configuration use cases
│   │   └── di/          # Config DI module
│   ├── developertools/   # Debug screens and developer utilities
│   │   ├── presentation/ # Developer tools UI
│   │   └── di/          # DeveloperTools DI module
│   ├── intro/           # App onboarding and terms acceptance
│   │   ├── domain/      # Intro page models and use cases
│   │   ├── presentation/ # Intro screen UI with pager
│   │   └── di/          # Intro DI module
│   ├── settings/        # User preferences and app settings
│   │   ├── domain/      # Settings use cases (theme, preferences)
│   │   ├── presentation/ # Settings screen UI
│   │   └── di/          # Settings DI module
│   └── showcase/        # Component library showcase
│       ├── presentation/ # Showcase screen demonstrating all components
│       └── di/          # Showcase DI module
├── navigation/           # App navigation setup
│   ├── AppNavigation.kt  # NavHost with all app routes
│   └── AppRoutes.kt     # Type-safe navigation routes
├── shared/              # Shared models across features
│   ├── composables/     # Reusable Compose utilities
│   └── model/          # Cross-feature domain models
└── ui/                 # Design system and UI components
    ├── components/     # Complete UI component library (30+ components)
    ├── composables/    # Animation and state utilities
    ├── modifiers/      # Custom Compose modifiers
    ├── theme/         # Complete design system
    └── utils/         # UI utilities and effect handlers
```

### Platform-Specific Structure
```
composeApp/
├── src/
│   ├── commonMain/          # Shared code across platforms
│   │   ├── kotlin/          # Common Kotlin code (feature-based)
│   │   └── composeResources/ # Shared resources (strings, drawables)
│   ├── androidMain/         # Android-specific implementations
│   │   └── kotlin/com/nickstamp/kit/
│   │       ├── MainActivity.kt        # Android app entry point
│   │       ├── Platform.android.kt    # Android platform implementation
│   │       ├── core/helpers/         # Android system helpers
│   │       ├── core/network/         # Android HTTP client (OkHttp)
│   │       ├── core/storage/         # Android DataStore factory
│   │       ├── di/                   # Android DI modules
│   │       └── ui/                   # Android-specific UI (WebView, EffectHandler)
│   ├── iosMain/            # iOS-specific implementations
│   │   └── kotlin/com/nickstamp/kit/
│   │       ├── MainViewController.kt  # iOS app entry point
│   │       ├── Platform.ios.kt       # iOS platform implementation
│   │       ├── core/helpers/         # iOS system helpers
│   │       ├── core/network/         # iOS HTTP client (Darwin)
│   │       ├── core/storage/         # iOS DataStore factory
│   │       ├── di/                   # iOS DI modules
│   │       └── ui/                   # iOS-specific UI (WebView, EffectHandler)
│   └── commonTest/         # Shared test code
└── build.gradle.kts        # Module build configuration
```

## MVI Implementation Pattern

### BaseViewModel
All ViewModels must extend this base class:

```kotlin
// core/arch/BaseViewModel.kt
abstract class BaseViewModel<EVENT, EFFECT, STATE>(initialState: STATE) : ViewModel() {

    private val _effect = Channel<EFFECT>()
    val effect = _effect.receiveAsFlow()

    private val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    abstract fun onEvent(event: EVENT)

    protected fun launchInViewModelScope(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(block = block)

    protected fun setState(reduce: STATE.() -> STATE) = launchInViewModelScope {
        val newState = _state.value.reduce()
        _state.value = newState
    }

    protected fun setEffect(effect: EFFECT) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }
}
```

### Screen Structure
Each screen must follow this exact structure:

#### 1. Contract (defines the screen's interface)
```kotlin
// presentation/[ScreenName]Contract.kt
interface [ScreenName]Contract {
    data class State(
        val isLoading: Boolean = false,
        val data: List<SomeData> = emptyList(),
        val error: String? = null
    )
    
    sealed interface Event {
        data object LoadData : Event
        data class OnItemClick(val item: SomeData) : Event
    }
    
    sealed interface Effect {
        data class NavigateToDetail(val id: String) : Effect
        data class ShowError(val message: String) : Effect
    }
}
```

#### 2. ViewModel (extends BaseViewModel)
```kotlin
// presentation/[ScreenName]ViewModel.kt
class [ScreenName]ViewModel(
    private val useCase: SomeUseCase, // DI injected
    private val mapper: SomeMapper    // DI injected
) : BaseViewModel<[ScreenName]Contract.Event, [ScreenName]Contract.Effect, [ScreenName]Contract.State>(
    initialState = [ScreenName]Contract.State()
) {
    
    override fun onEvent(event: [ScreenName]Contract.Event) {
        when (event) {
            is [ScreenName]Contract.Event.LoadData -> loadData()
            is [ScreenName]Contract.Event.OnItemClick -> onItemClick(event.item)
        }
    }
    
    private fun loadData() {
        launchInViewModelScope {
            setState { copy(isLoading = true) }
            // Business logic here
        }
    }
}
```

#### 3. Screen Route (handles navigation and effects)
```kotlin
// presentation/[ScreenName]ScreenRoute.kt
@Composable
fun [ScreenName]ScreenRoute(
    id: String,
    onNavigateBack: () -> Unit,
    effectHandler: EffectHandler,
    viewModel: [ScreenName]ViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.onEvent([ScreenName]Contract.Event.LoadDetails(id))
    }

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is [ScreenName]Contract.Effect.NavigateBack -> onNavigateBack()
                is [ScreenName]Contract.Effect.ShowError -> effectHandler.showToast(ToastInfo(effect.message))
            }
        }
    }

    [ScreenName]Screen(
        state = state,
        onEvent = viewModel::onEvent
    )
}
```

#### 4. Screen Composable (pure UI)
```kotlin
// presentation/[ScreenName]Screen.kt
@Composable
fun [ScreenName]Screen(
    state: [ScreenName]Contract.State,
    onEvent: ([ScreenName]Contract.Event) -> Unit
) {
    // Pure UI implementation
    // No business logic, only UI state and event callbacks
}
```

## Complete Design System

### Theme Architecture
The framework includes a comprehensive design system with:

#### Color System
- **Light/Dark themes**: Complete Material 3 color schemes
- **Semantic colors**: Success, Warning, Info, Error with containers
- **Extended colors**: Beyond Material 3 palette
- **Emphasis levels**: 5 levels of opacity for content hierarchy
- **Platform flavor colors**: Blue primary, Green secondary, Orange tertiary

#### Typography System
- **18 font sizes**: From 8sp to 36sp
- **4 font weights**: Regular, Bold, Italic, BoldItalic combinations
- **Consistent line heights**: Optimized for readability
- **Accessible text styles**: Following Material Design guidelines

#### Spacing System
```kotlin
data class Dimens(
    val none: Dp = 0.dp,
    val xsmall: Dp = 2.dp,
    val small: Dp = 4.dp,
    val default: Dp = 8.dp,
    val medium: Dp = 12.dp,
    val large: Dp = 16.dp,
    val xlarge: Dp = 20.dp,
    val xxlarge: Dp = 24.dp,
    val xxxlarge: Dp = 32.dp,
    // ... and more
)
```

#### Shape System
- **Basic shapes**: Rectangle, Circle, Rounded corners
- **Directional rounding**: Top, Bottom, Start, End specific
- **Percentage rounding**: 25%, 50%, 75% rounded corners
- **Context-specific shapes**: App bar, cards, buttons

#### Component Library (30+ Components)
```kotlin
// Navigation & Layout
- KitAppBar              // Customizable top app bar with logo, actions
- KitAppBarIcon          // App bar action icons
- KitAppBarTitle         // App bar title component
- KitCollapsingLayout    // Collapsible content layout

// Input & Interaction
- KitTextInput           // Material text field with icons
- KitIconButton          // Icon-based buttons
- KitButtonGroup         // Segmented button groups
- KitFilterButton        // Filter/sort buttons
- KitFavoriteButton      // Heart/favorite toggle

// Data Display
- KitNetworkImage        // Image loading with Coil
- KitLabel               // Text labels with icons
- KitInfoLabel           // Status badges (New, Beta, Hot)
- KitKeyValueChip        // Key-value pair display
- KitTextWithIcon        // Text with leading/trailing icons

// Feedback & Status
- KitSkeleton            // Loading skeleton animations
- KitPulsatingCircle     // Loading indicators
- KitHorizontalProgressBar // Horizontal progress indicators
- KitVerticalProgressBar   // Vertical progress indicators
- KitEmptyDataViewCard     // Empty state with retry
- KitEmptyResultsMessage   // No results message

// Layout & Structure
- KitSectionContainer    // Section with title and optional action
- KitFooterCard          // Action card with background image
- KitBannerAdPlaceholder // Ad placement component

// Web Content
- HtmlWebView           // Cross-platform HTML display
```

### Animation System
- **Skeleton animations**: Shimmer effects for loading states
- **Safe click**: Debounced click handling to prevent double-taps
- **Opacity animations**: Smooth content transitions
- **Scrollable state**: Enhanced scroll state management

## Network Layer Architecture

### HTTP Client Configuration
```kotlin
// Platform-specific implementations
// Android: OkHttp engine
// iOS: Darwin engine
expect fun httpClient(): HttpClient

// Common configuration
fun createHttpClient(): HttpClient {
    return HttpClient {
        install(ContentNegotiation) { JsonConfig.instance }
        install(Logging) { level = LogLevel.ALL }
    }
}
```

### API Service Layer
```kotlin
class ApiService(internal val httpClient: HttpClient) {
    suspend inline fun <reified T> get(endpoint: String, baseUrl: String? = null): T
    suspend inline fun <reified T> post(endpoint: String, body: Any, baseUrl: String? = null): T
    suspend inline fun <reified T> put(endpoint: String, body: Any, baseUrl: String? = null): T
    suspend inline fun <reified T> delete(endpoint: String, baseUrl: String? = null): T
    
    // With EndpointBuilder support
    suspend inline fun <reified T> get(endpointBuilder: EndpointBuilder): T
}
```

### Error Handling
```kotlin
sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val exception: ApiException) : ApiResult<Nothing>()
}

sealed class ApiException(message: String, cause: Throwable? = null) : Exception(message, cause) {
    data class NetworkError(val originalException: Throwable) : ApiException(...)
    data class HttpError(val statusCode: HttpStatusCode, val errorBody: String?) : ApiException(...)
    data class SerializationError(val originalException: Throwable) : ApiException(...)
    data class UnknownError(val originalException: Throwable) : ApiException(...)
}
```

### Endpoint Building
```kotlin
// Flexible URL construction
val endpoint = "users/{id}/profile"
    .toEndpoint("https://api.example.com")
    .withPathParam("id", "123")
    .withQueryParam("include", "details")
    .build()
// Result: https://api.example.com/users/123/profile?include=details
```

## Data Persistence Architecture

### DataStore Management
```kotlin
interface DatastoreManager {
    // Type-safe operations for all primitive types
    suspend fun saveString(key: String, value: String)
    suspend fun getString(key: String, defaultValue: String = ""): String
    fun getStringFlow(key: String, defaultValue: String = ""): Flow<String>
    
    // Similar methods for Int, Boolean, Long, Float, Double, Set<String>
    // Utility operations
    suspend fun remove(key: String)
    suspend fun clear()
    suspend fun contains(key: String): Boolean
}
```

### Platform-Specific DataStore
```kotlin
// Android implementation uses Android DataStore
// iOS implementation uses NSUserDefaults with DataStore wrapper
expect fun createDataStore(): DataStore<Preferences>
```

## Configuration System Architecture

### Remote Configuration
The framework includes a sophisticated remote configuration system for dynamic app control:

#### Configuration Domain Models
```kotlin
data class Configuration(
    val upToDate: Boolean,
    val appUpdateConfig: AppUpdateConfig,      // Platform-specific update management
    val appRateConfig: AppRateConfig,          // Rating prompt configuration
    val adPlacements: List<AdPlacementConfig>, // Dynamic ad placement
    val appLaunchAnnouncement: Announcement,   // In-app announcements
    val appIntroConfig: AppIntroConfig         // Onboarding configuration
)

data class AppUpdateConfig(
    val ios: PlatformUpdateConfig,
    val android: PlatformUpdateConfig
)

data class PlatformUpdateConfig(
    val minimumRequiredVersion: Int,    // Mandatory update threshold
    val latestVersion: Int,             // Current latest version
    val downloadUrl: String             // Store or CDN download URL
)
```

#### DTO with Safe Getters
```kotlin
@Serializable
data class ConfigurationDto(
    @SerialName("utd") internal val _upToDate: Boolean? = false,
    @SerialName("update") internal val _appUpdateConfig: AppUpdateConfigDto? = AppUpdateConfigDto(),
    // ... other fields
) {
    // Safe public getters with default fallbacks
    val upToDate: Boolean get() = _upToDate ?: false
    val appUpdateConfig: AppUpdateConfigDto get() = _appUpdateConfig ?: AppUpdateConfigDto()
}
```

### Update Management System
```kotlin
// Platform-aware update checking
interface AppUpdateHelper {
    suspend fun checkForUpdates(): AppUpdateStatus
    suspend fun isUpdateRequired(remoteVersion: Int): Boolean
    suspend fun getCurrentAppVersion(): Int
    fun getStoreUrl(): String
}

sealed class AppUpdateStatus {
    data object UpToDate : AppUpdateStatus()
    data class UpdateAvailable(val isRequired: Boolean, val downloadUrl: String) : AppUpdateStatus()
    data class UpdateRequired(val downloadUrl: String) : AppUpdateStatus()
}
```

## Feature Modules Deep Dive

### App Launcher Feature
- **Purpose**: Handles app startup, splash screen, and update checking
- **Components**: 
  - Splash screen with app logo
  - Update detection and enforcement
  - Navigation to appropriate initial screen (intro vs main app)
- **Update flow**: Check remote config → Compare versions → Show update dialog if needed

### Configuration Feature
- **Purpose**: Manages remote app configuration and feature flags
- **Components**:
  - Remote API integration with caching
  - Local configuration storage
  - Mapper from DTO to domain models
  - Configuration refresh and validation
- **Cache strategy**: Network-first with local fallback

### Intro Feature
- **Purpose**: App onboarding and terms acceptance
- **Components**:
  - Multi-page intro with pager
  - Terms and conditions display (HTML content)
  - Terms acceptance tracking
  - Skip intro for returning users
- **Content management**: HTML content from remote configuration

### Settings Feature
- **Purpose**: User preferences and app configuration
- **Components**:
  - Theme selection (Light/Dark/System)
  - Developer tools access
  - App version display
  - Settings persistence with DataStore
- **Theme management**: Real-time theme switching with DataStore persistence

### Developer Tools Feature
- **Purpose**: Debug utilities and development aids
- **Components**:
  - Component showcase access
  - Intro screen testing
  - Developer settings
  - Debug information display
- **Access control**: Available in debug builds or via settings

### Showcase Feature
- **Purpose**: Visual component library documentation
- **Components**:
  - Interactive demos of all UI components
  - Component descriptions and usage examples
  - Live component testing
  - Design system verification
- **Coverage**: Demonstrates all 30+ framework components

## Dependency Injection Architecture

### Module Structure
```kotlin
// Core framework modules
val coreModule = module {
    single { httpClient() }                    // Platform-specific HTTP client
    singleOf(::ApiService)                     // Generic API service
    single { createDataStore() }               // Platform-specific DataStore
    single<DatastoreManager> { DatastoreManagerImpl(get()) }
    singleOf(::DateTimeHelperImpl) bind DateTimeHelper::class
}

// Platform-specific modules
val androidModule = module {
    single<EffectHandler> { AndroidEffectHandler(get()) }
    single<SystemHelper> { AndroidSystemHelper(androidContext()) }
}

val iosModule = module {
    single<EffectHandler> { IosEffectHandler() }
    single<SystemHelper> { IosSystemHelper() }
}

// Feature modules
val configModule = module {
    // Data layer
    singleOf(::ConfigurationRemoteDataSourceImpl) bind ConfigurationRemoteDataSource::class
    singleOf(::ConfigurationLocalDataSourceImpl) bind ConfigurationLocalDataSource::class
    singleOf(::ConfigurationRepositoryImpl) bind ConfigurationRepository::class
    
    // Domain layer
    singleOf(::GetConfigurationUseCase)
    
    // Presentation layer (ViewModels injected automatically)
}
```

### Platform-Specific DI Setup
```kotlin
// Android
@Composable
actual fun KoinConfig(content: @Composable () -> Unit) {
    val context = LocalContext.current
    KoinApplication(application = {
        androidContext(context)
        modules(androidModule, appModule, coreModule, /*feature modules*/)
    }) { content() }
}

// iOS
@Composable
actual fun KoinConfig(content: @Composable () -> Unit) {
    KoinApplication(application = {
        modules(iosModule, appModule, coreModule, /*feature modules*/)
    }) { content() }
}
```

## Navigation Architecture

### Type-Safe Navigation
```kotlin
@Serializable
sealed class AppRoutes {
    @Serializable data object AppLauncher : AppRoutes()
    @Serializable data object Settings : AppRoutes()
    @Serializable data object Showcase : AppRoutes()
    @Serializable data object Intro : AppRoutes()
    @Serializable data object DeveloperTools : AppRoutes()
}
```

### Navigation Flow
1. **App Launch** → Check if intro needed → Navigate to appropriate screen
2. **Settings** → Access to all app features and developer tools
3. **Developer Tools** → Access to showcase and debug features
4. **Deep Navigation**: Support for direct navigation to any feature

## Cross-Platform Utilities

### Platform Detection
```kotlin
interface Platform {
    val name: String
    val version: String
    val isDebug: Boolean
}

expect fun getPlatform(): Platform
```

### System Helpers
```kotlin
interface SystemHelper {
    fun getCurrentVersion(): Int           // App version code
    fun getCurrentVersionName(): String    // App version name
    fun getAppStoreUrl(): String          // Platform-specific store URL
    fun isAppleEnvironment(): Boolean     // iOS detection
}
```

### Date/Time Utilities
```kotlin
interface DateTimeHelper {
    fun minutesToMilliseconds(minutes: Int): Long
    fun getCurrentTimeInMillis(): Long
    fun format(timestamp: Long, format: String): String
    fun getCurrentLocalDateTime(): LocalDateTime
    // ... more date utilities
}
```

### Internationalization
```kotlin
sealed class UiText {
    data object Empty : UiText()
    data class DynamicString(val value: String) : UiText()
    data class Resource(val id: StringResource, val args: List<Any>) : UiText()
    data class PluralResource(val id: PluralStringResource, val value: Int) : UiText()
}

@Composable
fun UiText.asString(): String // Resolves to actual string based on type
```

## Framework Dependencies

### Complete Dependency List
```toml
[versions]
kotlin = "2.2.0"
composeMultiplatform = "1.8.2"
koin = "4.1.0"
ktor = "3.2.1"
navigation = "2.9.0-beta03"
coil = "3.2.0"
datastore = "1.1.7"
kotlinx-datetime = "0.7.0"
kotlinx-serialization = "1.9.0"
kotlinx-coroutines = "1.10.2"
androidx-lifecycle = "2.9.1"
android-compileSdk = "35"
android-minSdk = "24"
android-targetSdk = "35"

[libraries]
# Architecture
androidx-lifecycle-viewmodel = { module = "org.jetbrains.androidx.lifecycle:lifecycle-viewmodel" }
androidx-lifecycle-runtimeCompose = { module = "org.jetbrains.androidx.lifecycle:lifecycle-runtime-compose" }

# Dependency Injection
koin-core = { module = "io.insert-koin:koin-core" }
koin-compose = { module = "io.insert-koin:koin-compose" }
koin-compose-viewmodel = { module = "io.insert-koin:koin-compose-viewmodel" }
koin-android = { module = "io.insert-koin:koin-android" }

# Networking
ktor-client-core = { module = "io.ktor:ktor-client-core" }
ktor-client-content-negotiation = { module = "io.ktor:ktor-client-content-negotiation" }
ktor-serialization-kotlinx-json = { module = "io.ktor:ktor-serialization-kotlinx-json" }
ktor-client-logging = { module = "io.ktor:ktor-client-logging" }
ktor-client-okhttp = { module = "io.ktor:ktor-client-okhttp" }        # Android
ktor-client-darwin = { module = "io.ktor:ktor-client-darwin" }        # iOS

# UI & Navigation
navigation-compose = { module = "org.jetbrains.androidx.navigation:navigation-compose" }
coil-compose = { module = "io.coil-kt.coil3:coil-compose" }
coil-network-okhttp = { module = "io.coil-kt.coil3:coil-network-okhttp" }

# Data Persistence
datastore-core = { module = "androidx.datastore:datastore-core" }
datastore-preferences = { module = "androidx.datastore:datastore-preferences-core" }
datastore-preferences-android = { module = "androidx.datastore:datastore-preferences" }

# Utilities
kotlinx-datetime = { module = "org.jetbrains.kotlinx:kotlinx-datetime" }
kotlinx-serialization-json = { module = "org.jetbrains.kotlinx:kotlinx-serialization-json" }
kotlinx-coroutines-core = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-core" }
```

## Development Guidelines

### Code Organization Rules
1. **Feature-based packages**: Never organize by technical layers (data, domain, presentation)
2. **Dependency Injection**: All dependencies must be injected via Koin - no direct instantiation
3. **MVI Pattern**: Every screen must have Contract, ViewModel (extending BaseViewModel), ScreenRoute, and Screen
4. **BaseViewModel**: All ViewModels must extend BaseViewModel<EVENT, EFFECT, STATE>
5. **Separation of Concerns**: ScreenRoute handles navigation/effects, Screen handles pure UI
6. **Single Responsibility**: Each class should have one clear responsibility
7. **Clean Architecture**: Strict separation between layers with dependency inversion

### Naming Conventions
- **Screen Routes**: `[FeatureName]ScreenRoute.kt` (handles navigation and effects)
- **Screen Composables**: `[FeatureName]Screen.kt` (pure UI)
- **ViewModels**: `[FeatureName]ViewModel.kt` (extends BaseViewModel)
- **Contracts**: `[FeatureName]Contract.kt` (Event, Effect, State)
- **Use Cases**: `[Action][FeatureName]UseCase.kt`
- **Repositories**: `[FeatureName]Repository.kt`
- **Mappers**: `[FeatureName]Mapper.kt`
- **DTOs**: `[FeatureName]Dto.kt`
- **DI Modules**: `[FeatureName]Module.kt`

### Testing Strategy
- Unit tests for ViewModels and Use Cases
- Integration tests for Repositories
- UI tests for Composables
- All tests in `commonTest` for shared logic

## Framework Usage Guide

### Getting Started
1. **Clone and Setup**:
   ```bash
   git clone <repository>
   cd Kit-CMP
   ```

2. **Customize for Your Project**:
   - Change package name from `com.nickstamp.kit` to `com.yourcompany.yourapp`
   - Update app name in `composeResources/values/strings.xml`
   - Replace placeholder content in string resources with your app's content
   - Configure your API endpoints in `ConfigurationEndpoints.kt` (replace placeholder URL)
   - Update terms of service placeholders `[APP_NAME]` and `[CONTACT_EMAIL]` with your values

3. **Add Your First Feature**:
   ```kotlin
   // 1. Create feature package structure
   feature/yourfeature/
   ├── data/
   ├── domain/
   ├── presentation/
   └── di/
   
   // 2. Follow MVI pattern
   // 3. Add to navigation
   // 4. Register DI module
   ```

4. **Configure Remote Settings**:
   - Set up your configuration API endpoint in `ConfigurationEndpoints.kt`
   - Customize configuration models for your needs
   - Update JSON response format in DTO classes
   - Replace example.json placeholders with your actual configuration structure

### Framework Customization Checklist
- [ ] Update package name throughout the project
- [ ] Replace `Kit-CMP` app name with your app name
- [ ] Configure your configuration API endpoint
- [ ] Replace `[APP_NAME]` placeholders in terms of service
- [ ] Replace `[CONTACT_EMAIL]` placeholders with your contact email
- [ ] Update app store URLs in configuration examples
- [ ] Customize intro page content for your app's purpose
- [ ] Add your app-specific features following the established patterns

### Best Practices
- **Always use the design system**: Access colors, spacing, typography through `AppTheme`
- **Follow MVI strictly**: Keep business logic in ViewModels, UI logic in Composables
- **Inject everything**: No direct instantiation, use Koin for all dependencies
- **Platform abstraction**: Use expect/actual for platform-specific code
- **Resource management**: Use Compose Resources for cross-platform assets
- **Error handling**: Use ApiResult wrapper for network calls
- **Safe operations**: Use safe click modifiers to prevent double-taps

### Framework Extension Points
1. **Add new UI components**: Create in `ui/components/` and showcase in `ShowcaseScreen`
2. **Add new features**: Follow the established feature module structure
3. **Extend configuration**: Add new fields to configuration DTOs and domain models
4. **Platform-specific logic**: Use expect/actual declarations
5. **Custom network logic**: Extend `ApiService` or create feature-specific services
6. **New storage needs**: Extend `DatastoreManager` or create custom storage solutions

## Platform-Specific Notes

### Android Specifics
- **Entry point**: `MainActivity.kt` with Activity Compose
- **HTTP client**: OkHttp engine for optimal Android performance
- **Storage**: Android DataStore with coroutine support
- **System info**: Access via Android Context and PackageManager
- **WebView**: Native Android WebView with HTML support
- **Effects**: Toast notifications and system app closure

### iOS Specifics
- **Entry point**: `MainViewController.kt` for iOS app integration
- **HTTP client**: Darwin engine for native iOS networking
- **Storage**: NSUserDefaults wrapper with DataStore interface
- **System info**: Access via iOS APIs and app bundle info
- **WebView**: iOS-specific HTML rendering (implementation pending)
- **Effects**: iOS-native notifications and app lifecycle management

### Cross-Platform Features
- **UI**: 100% shared Compose Multiplatform UI
- **Business logic**: Shared ViewModels and use cases
- **Data models**: Common domain models and DTOs
- **Navigation**: Shared navigation logic and routes
- **Theme**: Unified design system across platforms
- **Configuration**: Shared remote configuration system

## Development Memories

### Code Principles
- **Design system first**: Always use the framework's design system for consistency
- **Remote configuration**: Use the configuration system for any app behavior that might need to change
- **Platform abstraction**: Keep platform-specific code minimal and well-abstracted
- **Type safety**: Leverage Kotlin's type system for safer code (sealed classes, data classes)
- **Immutability**: Prefer immutable data structures and state management

### Shared Components Rules
- **Showcase integration**: When adding new shared components, always demonstrate them in `ShowcaseScreen.kt`
- **Design system compliance**: All components must use the established color, spacing, and typography tokens
- **Composable guidelines**: Follow Compose best practices for reusability and performance
- **Documentation**: Include kdoc comments explaining component purpose and usage

### Configuration System Guidelines
- **Platform awareness**: Update configurations should be platform-specific (iOS/Android)
- **Safe defaults**: All configuration DTOs must provide safe defaults through null-safe public getters
- **Backward compatibility**: New configuration fields should be optional with sensible defaults
- **Caching strategy**: Remote configuration should work offline with local caching
- **Validation**: Validate configuration data and provide fallbacks for invalid data

### Architecture Enforcement
- **MVI compliance**: Every screen must follow the established MVI pattern without exceptions
- **Dependency injection**: No direct instantiation - everything goes through Koin
- **Feature isolation**: Features should be self-contained with minimal cross-feature dependencies
- **Clean boundaries**: Maintain strict separation between data, domain, and presentation layers
- **Error handling**: All network operations must use the `ApiResult` wrapper for consistent error handling