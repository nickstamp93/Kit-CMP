package com.nickstamp.kit.feature.settings.domain.usecase

import com.nickstamp.kit.core.storage.example.DefaultDatastoreManager

class SetAppThemeUseCase(
    private val defaultDatastoreManager: DefaultDatastoreManager
) {
    suspend operator fun invoke(isDarkTheme: Boolean): Result<Unit> {
        return try {
            defaultDatastoreManager.saveDarkTheme(isDarkTheme)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}