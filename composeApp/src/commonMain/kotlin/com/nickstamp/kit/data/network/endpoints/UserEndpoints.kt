package com.nickstamp.kit.data.network.endpoints

import com.nickstamp.kit.core.network.EndpointBuilder

object UserEndpoints {

    // Simple endpoint constants
    const val USERS = "users"
    const val USER_BY_ID = "users/{id}"
    const val USERS_BY_ROLE = "users/role/{roleType}"
    const val USER_AVATAR_UPLOAD = "upload/avatar/{userId}"

    // Factory methods for common patterns
    fun getUser(id: String): EndpointBuilder {
        return EndpointBuilder.Companion.create(USER_BY_ID)
            .withPathParam("id", id)
    }

    fun getUsersByRole(role: String, activeOnly: Boolean = true): EndpointBuilder {
        return EndpointBuilder.Companion.create(USERS_BY_ROLE)
            .withPathParam("roleType", role)
            .apply {
                if (activeOnly) withQueryParam("active", "true")
            }
    }

    fun getUsersPaginated(page: Int? = null, limit: Int? = null): EndpointBuilder {
        val builder = EndpointBuilder.Companion.create(USERS)
        page?.let { builder.withQueryParam("page", it.toString()) }
        limit?.let { builder.withQueryParam("limit", it.toString()) }
        return builder
    }

    fun uploadUserAvatar(userId: String): EndpointBuilder {
        return EndpointBuilder.Companion.createWithNamedBaseUrl(USER_AVATAR_UPLOAD, "cdn")
            .withPathParam("userId", userId)
    }

    // For very simple cases, you can still use direct strings
    fun createUser(): String = USERS
    fun updateUser(id: String): String = "users/$id"
    fun deleteUser(id: String): String = "users/$id"
}