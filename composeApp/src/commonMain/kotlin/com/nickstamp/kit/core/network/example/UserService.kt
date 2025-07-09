package com.nickstamp.kit.core.network.example

import com.nickstamp.kit.core.network.ApiResult
import com.nickstamp.kit.core.network.ApiService
import com.nickstamp.kit.core.network.endpoints.UserEndpoints
import com.nickstamp.kit.core.network.safeApiCall
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val email: String
)

@Serializable
data class CreateUserRequest(
    val name: String,
    val email: String
)

@Serializable
data class UpdateUserRequest(
    val name: String?,
    val email: String?
)

interface UserService {
    suspend fun getUser(id: String): ApiResult<User>
    suspend fun getUsers(page: Int? = null, limit: Int? = null): ApiResult<List<User>>
    suspend fun getUsersByRole(role: String): ApiResult<List<User>>
    suspend fun createUser(user: CreateUserRequest): ApiResult<User>
    suspend fun updateUser(id: String, user: UpdateUserRequest): ApiResult<User>
    suspend fun deleteUser(id: String): ApiResult<Unit>
    suspend fun uploadUserAvatar(userId: String, avatar: Any): ApiResult<User>
}

class UserServiceImpl(
    private val apiService: ApiService
) : UserService {
    
    // Using endpoint factory methods - clean and maintainable
    override suspend fun getUser(id: String): ApiResult<User> = safeApiCall {
        apiService.get<User>(UserEndpoints.getUser(id))
    }
    
    override suspend fun getUsers(page: Int?, limit: Int?): ApiResult<List<User>> = safeApiCall {
        apiService.get<List<User>>(UserEndpoints.getUsersPaginated(page, limit))
    }
    
    override suspend fun getUsersByRole(role: String): ApiResult<List<User>> = safeApiCall {
        apiService.get<List<User>>(UserEndpoints.getUsersByRole(role))
    }
    
    // Using simple string constants for basic cases
    override suspend fun createUser(user: CreateUserRequest): ApiResult<User> = safeApiCall {
        apiService.post<User>(UserEndpoints.createUser(), user)
    }
    
    override suspend fun updateUser(id: String, user: UpdateUserRequest): ApiResult<User> = safeApiCall {
        apiService.put<User>(UserEndpoints.updateUser(id), user)
    }
    
    override suspend fun deleteUser(id: String): ApiResult<Unit> = safeApiCall {
        apiService.delete<Unit>(UserEndpoints.deleteUser(id))
    }
    
    override suspend fun uploadUserAvatar(userId: String, avatar: Any): ApiResult<User> = safeApiCall {
        apiService.post<User>(UserEndpoints.uploadUserAvatar(userId), avatar)
    }
}