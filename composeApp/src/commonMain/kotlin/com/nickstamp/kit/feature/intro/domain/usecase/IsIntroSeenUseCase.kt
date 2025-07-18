package com.nickstamp.kit.feature.intro.domain.usecase

import com.nickstamp.kit.core.storage.example.DefaultDatastoreManager

class IsIntroSeenUseCase(
    private val defaultDatastoreManager: DefaultDatastoreManager
) {
    suspend operator fun invoke(): Boolean {
        return try {
            defaultDatastoreManager.isIntroSeen()
        } catch (e: Exception) {
            false
        }
    }
}