# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview
This is a Kotlin Multiplatform project targeting Android and iOS platforms using Compose Multiplatform. The project follows the standard KMP structure with shared code in `commonMain` and platform-specific implementations in `androidMain` and `iosMain`.

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

## Project Structure
```
composeApp/
├── src/
│   ├── commonMain/          # Shared code across platforms
│   │   ├── kotlin/          # Common Kotlin code
│   │   └── composeResources/ # Shared Compose resources
│   ├── androidMain/         # Android-specific code
│   ├── iosMain/            # iOS-specific code
│   └── commonTest/         # Shared test code
└── build.gradle.kts        # Module build configuration
```

## Key Architecture Patterns
- **Platform abstraction**: Uses `expect`/`actual` declarations for platform-specific implementations (see `Platform.kt`)
- **Compose Multiplatform**: UI built with Compose that works across Android and iOS
- **Resource management**: Uses Compose Resources for cross-platform asset management
- **Namespace**: All code uses `com.nickstamp.kit` package structure

## Dependencies Management
- Dependencies are managed through `gradle/libs.versions.toml` version catalog
- Compose Multiplatform version: 1.8.2
- Kotlin version: 2.2.0
- Target SDK: Android 35, minimum SDK: 24
- iOS targets: iosX64, iosArm64, iosSimulatorArm64

## Platform-Specific Notes
- **Android**: Entry point in `MainActivity.kt`, uses Activity Compose
- **iOS**: Framework generated for iOS app integration, entry point in `MainViewController.kt`
- **Common**: Main app UI in `App.kt`, platform detection via `getPlatform()`