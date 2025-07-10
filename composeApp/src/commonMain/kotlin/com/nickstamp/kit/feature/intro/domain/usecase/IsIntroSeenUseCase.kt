package com.nickstamp.kit.feature.intro.domain.usecase

import com.nickstamp.kit.core.storage.DatastoreManager

class IsIntroSeenUseCase(
    private val datastoreManager: DatastoreManager
) {
    suspend operator fun invoke(): Boolean {
        return try {
            datastoreManager.getBoolean(KEY_INTRO_SEEN, false)
        } catch (e: Exception) {
            false
        }
    }
    
    companion object {
        private const val KEY_INTRO_SEEN = "intro_seen"
    }
}