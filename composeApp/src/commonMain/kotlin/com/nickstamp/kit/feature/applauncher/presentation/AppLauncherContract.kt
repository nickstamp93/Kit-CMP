package com.nickstamp.kit.feature.applauncher.presentation

import androidx.compose.runtime.Immutable
import com.nickstamp.kit.feature.applauncher.domain.AppUpdateStatus
import com.nickstamp.kit.feature.applauncher.domain.AppUpdateStore
import com.nickstamp.kit.feature.config.domain.model.Announcement
import com.nickstamp.kit.feature.config.domain.model.AppUpdateConfig

interface AppLauncherContract {
    sealed class Event {
        data class OnUpdateAppClick(val store: AppUpdateStore) : Event()
        data object OnUpdateLaterClick : Event()
        data object OnCloseAppClick : Event()
        data object OnTryAgainConfigClick : Event()
        data class OnAnnouncementCtaClick(val announcement: Announcement) : Event()
    }

    sealed class Effect {
        data object GoToHome : Effect()
        data object GoToAppIntro : Effect()
        data object CloseApp : Effect()
        data class OpenWebUrl(val url: String) : Effect()
    }

    sealed class State {
        data object Loading : State()
        data object ConfigurationError : State()

        @Immutable
        data class AnnouncementAvailable(
            val announcement: Announcement
        ) : State()

        @Immutable
        data class UpdateAvailable(
            val updateConfig: AppUpdateConfig,
            val updateStatus: AppUpdateStatus.UpdateAvailable
        ) : State()
    }
}