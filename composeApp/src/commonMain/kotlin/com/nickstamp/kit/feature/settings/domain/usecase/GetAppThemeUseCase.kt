package com.nickstamp.kit.feature.settings.domain.usecase

import com.nickstamp.kit.core.storage.example.DefaultDatastoreManager
import kotlinx.coroutines.flow.Flow

class GetAppThemeUseCase(
    private val defaultDatastoreManager: DefaultDatastoreManager
) {
    suspend operator fun invoke(): Boolean {
        return try {
            defaultDatastoreManager.isDarkTheme()
        } catch (e: Exception) {
            false
        }
    }
}