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

### Package Structure by Feature
```
composeApp/src/commonMain/kotlin/com/nickstamp/kit/
├── core/                    # Core framework components
│   ├── di/                 # Dependency injection modules
│   ├── navigation/         # Navigation setup
│   ├── network/           # Network configuration
│   └── utils/             # Shared utilities
├── feature/               # Feature modules
│   └── [featureName]/     # Each feature contains:
│       ├── data/          # Data layer (repositories, DTOs, mappers)
│       ├── domain/        # Domain layer (use cases, entities)
│       └── presentation/  # Presentation layer (contract, viewmodel, composables)
└── shared/               # Shared components across features
    ├── components/       # Reusable UI components
    ├── theme/           # App theming
    └── resources/       # Shared resources
```

### Platform Structure
```
composeApp/
├── src/
│   ├── commonMain/          # Shared code across platforms
│   │   ├── kotlin/          # Common Kotlin code (feature-based)
│   │   └── composeResources/ # Shared Compose resources
│   ├── androidMain/         # Android-specific implementations
│   ├── iosMain/            # iOS-specific implementations
│   └── commonTest/         # Shared test code
└── build.gradle.kts        # Module build configuration
```

## MVI Implementation Pattern

### BaseViewModel
All ViewModels must extend this base class:

```kotlin
// core/presentation/BaseViewModel.kt
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

## Key Architecture Patterns
- **Platform abstraction**: Uses `expect`/`actual` declarations for platform-specific implementations
- **Compose Multiplatform**: UI built with Compose that works across Android and iOS
- **Resource management**: Uses Compose Resources for cross-platform asset management
- **Namespace**: All code uses `com.nickstamp.kit` package structure
- **Dependency Injection**: Everything is injected - no direct instantiation
- **Single Responsibility**: Each class has one clear responsibility

## Framework Dependencies
This framework includes a comprehensive set of dependencies for rapid development:

### Core Dependencies
- **Compose Multiplatform**: 1.8.2 - UI framework
- **Kotlin**: 2.2.0 - Programming language
- **Navigation**: Compose Navigation + Voyager alternatives
- **Dependency Injection**: Koin (core, compose, viewmodel)

### Networking & Data
- **Ktor**: HTTP client with serialization support
- **Kotlinx Serialization**: JSON serialization
- **DataStore**: Preferences and data storage
- **Multiplatform Settings**: Simple key-value storage

### UI & UX
- **Coil**: Image loading
- **Material Icons Extended**: Comprehensive icon set
- **Compose Animation**: Enhanced animations
- **Material3 Window Size**: Adaptive layouts

### Utilities
- **Kotlinx DateTime**: Date/time handling
- **Kotlinx Coroutines**: Async programming
- **UUID**: Unique identifier generation
- **Napier**: Logging framework
- **Immutable Collections**: Performance collections

### Architecture
- **AndroidX Lifecycle**: ViewModel and lifecycle management
- **Kotlin Test**: Testing framework

### Platform Targets
- **Android**: compileSdk 35, minSdk 24, targetSdk 35
- **iOS**: iosX64, iosArm64, iosSimulatorArm64

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

### Testing Strategy
- Unit tests for ViewModels and Use Cases
- Integration tests for Repositories
- UI tests for Composables
- All tests in `commonTest` for shared logic

## Platform-Specific Notes
- **Android**: Entry point in `MainActivity.kt`, uses Activity Compose
- **iOS**: Framework generated for iOS app integration, entry point in `MainViewController.kt`
- **Common**: Main app UI in `App.kt`, platform detection via `getPlatform()`

## Framework Usage
This framework serves as a foundation for new CMP projects. Start by:
1. Clone and rename the project
2. Update package names and namespace
3. Implement your first feature following the MVI pattern
4. Set up DI modules for your feature
5. Add navigation between screens

## Development Memories

### Code Principles
- For new features and components always use the design system

### Shared Components
- When new shared components are added, always add them to ShowcaseScreen.kt
- When new shared components are created always use the app's design system