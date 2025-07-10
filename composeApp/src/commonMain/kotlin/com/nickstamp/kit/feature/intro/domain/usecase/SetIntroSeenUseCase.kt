package com.nickstamp.kit.feature.intro.domain.usecase

import com.nickstamp.kit.core.storage.DatastoreManager

class SetIntroSeenUseCase(
    private val datastoreManager: DatastoreManager
) {
    suspend operator fun invoke(seen: Boolean): Result<Unit> {
        return try {
            datastoreManager.saveBoolean(KEY_INTRO_SEEN, seen)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    companion object {
        private const val KEY_INTRO_SEEN = "intro_seen"
    }
}