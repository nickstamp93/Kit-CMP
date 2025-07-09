# Network Layer

This directory contains the network layer implementation for the Kit-CMP project, providing a Retrofit-like type-safe API client.

## Components

### ApiService
The main API client providing GET, POST, PUT, DELETE methods with type safety.

### EndpointBuilder
Type-safe URL construction with support for path parameters, query parameters, and multiple base URLs.

### NetworkConfig
Configuration for base URLs and endpoints.

### ApiResult
Result wrapper for safe API calls with success/error states.

### SafeApiCall
Extension function for safe API calls with automatic error handling.

## Usage Examples

### Basic Usage
```kotlin
// Simple GET request
override suspend fun getUser(id: String): ApiResult<User> = safeApiCall {
    apiService.get<User>("users/$id")
}

// POST with body
override suspend fun createUser(user: CreateUserRequest): ApiResult<User> = safeApiCall {
    apiService.post<User>("users", user)
}
```

### Using Query Parameters
```kotlin
// GET with query parameters
override suspend fun getUsers(page: Int?, limit: Int?): ApiResult<List<User>> = safeApiCall {
    val params = mutableMapOf<String, String>()
    page?.let { params["page"] = it.toString() }
    limit?.let { params["limit"] = it.toString() }
    
    apiService.get<List<User>>("users", params)
}
```

### Using EndpointBuilder for Complex URLs
```kotlin
// Path parameters and query parameters
override suspend fun getUsersByRole(role: String): ApiResult<List<User>> = safeApiCall {
    val endpoint = "users/role/{roleType}"
        .toEndpoint()
        .withPathParam("roleType", role)
        .withQueryParam("active", "true")
    
    apiService.get<List<User>>(endpoint)
}

// Different base URL
override suspend fun uploadUserAvatar(userId: String, avatar: Any): ApiResult<User> = safeApiCall {
    val endpoint = "upload/avatar/{userId}"
        .toEndpointWithBaseUrl("cdn")
        .withPathParam("userId", userId)
    
    apiService.post<User>(endpoint, avatar)
}
```

### Recommended Pattern: Endpoint Objects

For better maintainability, create dedicated endpoint objects:

```kotlin
// 1. Create endpoint object with constants and factory methods
object UserEndpoints {
    const val USERS = "users"
    const val USER_BY_ID = "users/{id}"
    const val USERS_BY_ROLE = "users/role/{roleType}"
    
    fun getUser(id: String): EndpointBuilder {
        return EndpointBuilder.create(USER_BY_ID)
            .withPathParam("id", id)
    }
    
    fun getUsersByRole(role: String, activeOnly: Boolean = true): EndpointBuilder {
        return EndpointBuilder.create(USERS_BY_ROLE)
            .withPathParam("roleType", role)
            .apply {
                if (activeOnly) withQueryParam("active", "true")
            }
    }
    
    fun getUsersPaginated(page: Int? = null, limit: Int? = null): EndpointBuilder {
        val builder = EndpointBuilder.create(USERS)
        page?.let { builder.withQueryParam("page", it.toString()) }
        limit?.let { builder.withQueryParam("limit", it.toString()) }
        return builder
    }
    
    // Simple string methods for basic cases
    fun createUser(): String = USERS
    fun updateUser(id: String): String = "users/$id"
}

// 2. Service implementation using endpoint object
class UserServiceImpl(private val apiService: ApiService) : UserService {
    override suspend fun getUser(id: String): ApiResult<User> = safeApiCall {
        apiService.get<User>(UserEndpoints.getUser(id))
    }
    
    override suspend fun getUsers(page: Int?, limit: Int?): ApiResult<List<User>> = safeApiCall {
        apiService.get<List<User>>(UserEndpoints.getUsersPaginated(page, limit))
    }
    
    override suspend fun getUsersByRole(role: String): ApiResult<List<User>> = safeApiCall {
        apiService.get<List<User>>(UserEndpoints.getUsersByRole(role))
    }
    
    override suspend fun createUser(user: CreateUserRequest): ApiResult<User> = safeApiCall {
        apiService.post<User>(UserEndpoints.createUser(), user)
    }
    
    override suspend fun updateUser(id: String, user: UpdateUserRequest): ApiResult<User> = safeApiCall {
        apiService.put<User>(UserEndpoints.updateUser(id), user)
    }
}

// 3. Use in Repository
class UserRepository(private val userService: UserService) {
    suspend fun getUser(id: String): ApiResult<User> = userService.getUser(id)
}

// 4. Use in ViewModel
class UserViewModel(private val userRepository: UserRepository) : BaseViewModel<...> {
    private fun loadUser(id: String) {
        launchInViewModelScope {
            when (val result = userRepository.getUser(id)) {
                is ApiResult.Success -> {
                    setState { copy(user = result.data, isLoading = false) }
                }
                is ApiResult.Error -> {
                    setState { copy(error = result.exception.message, isLoading = false) }
                }
            }
        }
    }
}
```

## Features

- **Type Safety**: All API calls are type-safe with reified generics
- **Multiple Base URLs**: Support for different base URLs per request
- **URL Builder**: Fluent EndpointBuilder for complex URL construction
- **Path Parameters**: Type-safe path parameter replacement
- **Query Parameters**: Easy query parameter handling
- **Error Handling**: Comprehensive error handling with custom exceptions
- **Retrofit-like**: Familiar API similar to Retrofit
- **Kotlin Multiplatform**: Works on both Android and iOS
- **Dependency Injection**: Fully integrated with Koin DI

## EndpointBuilder API

The `EndpointBuilder` provides a fluent API for constructing complex URLs:

```kotlin
// Create endpoint builder
val endpoint = EndpointBuilder.create("users/{id}", baseUrl)
val endpoint = "users/{id}".toEndpoint(baseUrl)
val endpoint = "users/{id}".toEndpointWithBaseUrl("api")

// Add path parameters
endpoint.withPathParam("id", userId)

// Add query parameters
endpoint.withQueryParam("include", "profile")
endpoint.withQueryParams(mapOf("page" to "1", "limit" to "10"))

// Build final URL
val url = endpoint.build()
```

### EndpointBuilder Methods

- `create(endpoint, baseUrl?)` - Create builder with endpoint and optional base URL
- `createWithNamedBaseUrl(endpoint, baseUrlName)` - Create builder with named base URL
- `withPathParam(placeholder, value)` - Replace `{placeholder}` with value
- `withQueryParam(key, value)` - Add single query parameter
- `withQueryParams(params)` - Add multiple query parameters
- `build()` - Build final URL string

### Extension Functions

- `String.toEndpoint(baseUrl?)` - Convert string to EndpointBuilder
- `String.toEndpointWithBaseUrl(baseUrlName)` - Convert string with named base URL

## Configuration

Update `NetworkConfig.kt` with your API endpoints:

```kotlin
object NetworkConfig {
    const val DEFAULT_BASE_URL = "https://your-api.com/v1/"
    const val CDN_BASE_URL = "https://cdn.your-api.com/"
}
```

## Project Structure

```
core/network/
├── endpoints/
│   ├── UserEndpoints.kt       # User API endpoints
│   ├── ProductEndpoints.kt    # Product API endpoints
│   └── OrderEndpoints.kt      # Order API endpoints
├── example/
│   └── ImprovedUserService.kt # Example service implementation
├── ApiService.kt              # Main HTTP client
├── EndpointBuilder.kt         # URL builder utility
├── NetworkConfig.kt           # Base URL configuration
├── ApiResult.kt               # Result wrapper
├── SafeApiCall.kt            # Error handling
└── README.md                 # This file
```

## Best Practices

1. **Use Endpoint Objects**: Create dedicated objects for each API domain
2. **Centralize URLs**: Keep all endpoint definitions in one place per service
3. **Factory Methods**: Use factory methods for complex URL building
4. **Simple Strings**: Use direct strings for basic endpoints
5. **Consistent Naming**: Follow consistent naming conventions
6. **Type Safety**: Leverage type-safe builders for complex scenarios

## DI Setup

The network layer is automatically configured in `KoinModule.kt`:

```kotlin
val networkModule = module {
    single { ApiService(get()) }
    // Add your service implementations here
    single<UserService> { ImprovedUserServiceImpl(get()) }
}
```