package com.nickstamp.kit.data.network.service

import com.nickstamp.kit.core.network.ApiResult
import com.nickstamp.kit.core.network.ApiService
import com.nickstamp.kit.data.network.endpoints.UserEndpoints
import com.nickstamp.kit.core.network.safeApiCall
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String,
    val name: String,
    val email: String
)

@Serializable
data class CreateUserRequestDto(
    val name: String,
    val email: String
)

@Serializable
data class UpdateUserRequestDto(
    val name: String?,
    val email: String?
)

interface UserService {
    suspend fun getUser(id: String): ApiResult<UserDto>
    suspend fun getUsers(page: Int? = null, limit: Int? = null): ApiResult<List<UserDto>>
    suspend fun getUsersByRole(role: String): ApiResult<List<UserDto>>
    suspend fun createUser(user: CreateUserRequestDto): ApiResult<UserDto>
    suspend fun updateUser(id: String, user: UpdateUserRequestDto): ApiResult<UserDto>
    suspend fun deleteUser(id: String): ApiResult<Unit>
    suspend fun uploadUserAvatar(userId: String, avatar: Any): ApiResult<UserDto>
}

class UserServiceImpl(
    private val apiService: ApiService
) : UserService {
    
    // Using endpoint factory methods - clean and maintainable
    override suspend fun getUser(id: String): ApiResult<UserDto> = safeApiCall {
        apiService.get<UserDto>(UserEndpoints.getUser(id))
    }
    
    override suspend fun getUsers(page: Int?, limit: Int?): ApiResult<List<UserDto>> = safeApiCall {
        apiService.get<List<UserDto>>(UserEndpoints.getUsersPaginated(page, limit))
    }
    
    override suspend fun getUsersByRole(role: String): ApiResult<List<UserDto>> = safeApiCall {
        apiService.get<List<UserDto>>(UserEndpoints.getUsersByRole(role))
    }
    
    // Using simple string constants for basic cases
    override suspend fun createUser(user: CreateUserRequestDto): ApiResult<UserDto> = safeApiCall {
        apiService.post<UserDto>(UserEndpoints.createUser(), user)
    }
    
    override suspend fun updateUser(id: String, user: UpdateUserRequestDto): ApiResult<UserDto> = safeApiCall {
        apiService.put<UserDto>(UserEndpoints.updateUser(id), user)
    }
    
    override suspend fun deleteUser(id: String): ApiResult<Unit> = safeApiCall {
        apiService.delete<Unit>(UserEndpoints.deleteUser(id))
    }
    
    override suspend fun uploadUserAvatar(userId: String, avatar: Any): ApiResult<UserDto> = safeApiCall {
        apiService.post<UserDto>(UserEndpoints.uploadUserAvatar(userId), avatar)
    }
}