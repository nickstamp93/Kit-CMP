package com.nickstamp.kit.feature.intro.domain.usecase

import com.nickstamp.kit.core.storage.example.DefaultDatastoreManager

class SetIntroSeenUseCase(
    private val defaultDatastoreManager: DefaultDatastoreManager
) {
    suspend operator fun invoke(seen: Boolean): Result<Unit> {
        return try {
            defaultDatastoreManager.setIntroSeen(seen)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}