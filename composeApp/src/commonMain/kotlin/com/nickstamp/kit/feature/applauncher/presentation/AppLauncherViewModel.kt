package com.nickstamp.kit.feature.applauncher.presentation

import com.nickstamp.kit.core.analytics.domain.usecase.SendAnalyticsEventUseCase
import com.nickstamp.kit.core.arch.BaseViewModel
import com.nickstamp.kit.feature.applauncher.analytics.AppLauncherAnalytics
import com.nickstamp.kit.feature.applauncher.domain.AppUpdateStatus
import com.nickstamp.kit.feature.applauncher.domain.AppUpdateStore
import com.nickstamp.kit.feature.applauncher.helper.AppUpdateHelper
import com.nickstamp.kit.feature.applauncher.presentation.AppLauncherContract.Effect
import com.nickstamp.kit.feature.applauncher.presentation.AppLauncherContract.Event
import com.nickstamp.kit.feature.applauncher.presentation.AppLauncherContract.State
import com.nickstamp.kit.feature.config.domain.model.Announcement
import com.nickstamp.kit.feature.config.domain.model.AppUpdateConfig
import com.nickstamp.kit.feature.config.domain.usecase.GetConfigurationUseCase
import com.nickstamp.kit.feature.intro.domain.usecase.IsIntroSeenUseCase

class AppLauncherViewModel(
    private val getConfigurationUseCase: GetConfigurationUseCase,
    private val updateHelper: AppUpdateHelper,
    private val isIntroSeen: IsIntroSeenUseCase,
    private val sendAnalyticsEvent: SendAnalyticsEventUseCase
) : BaseViewModel<Event, Effect, State>(
    initialState = State.Loading
) {

    init {
        fetchConfiguration()
    }

    override fun onEvent(event: Event) {
        when (event) {
            is Event.OnCloseAppClick -> closeApp()
            is Event.OnTryAgainConfigClick -> tryAgainFetchConfig()
            is Event.OnUpdateAppClick -> updateApp(event.store)
            is Event.OnUpdateLaterClick -> updateLater()
            is Event.OnAnnouncementCtaClick -> handleAnnouncementCtaClick(event.announcement)
        }
    }

    private fun closeApp() {
        setEffect(Effect.CloseApp)
        sendAnalyticsEvent(AppLauncherAnalytics.getCloseAppEvent())
    }

    private fun tryAgainFetchConfig() {
        fetchConfiguration()
        sendAnalyticsEvent(AppLauncherAnalytics.getErrorTryAgainEvent())
    }

    private fun updateApp(store: AppUpdateStore) {
        setEffect(Effect.OpenWebUrl(store.rawUrl))
        sendAnalyticsEvent(AppLauncherAnalytics.getUpdateAppEvent())
    }

    private fun updateLater() {
        goToAppIntroOrMainApp()
        sendAnalyticsEvent(AppLauncherAnalytics.getUpdateLaterEvent())
    }

    private fun fetchConfiguration() = launchInViewModelScope {
        setState { State.Loading }
        try {
            getConfigurationUseCase(forceFetch = true)
            checkForAnnouncements(
                onAnnouncementsNotFound = {
                    checkForUpdates()
                }
            )
        } catch (e: Exception) {
            setState { State.ConfigurationError }
        }
    }

    private fun checkForAnnouncements(
        onAnnouncementsNotFound: () -> Unit
    ) = launchInViewModelScope {
        val config = getConfigurationUseCase()
        val announcement = config.appLaunchAnnouncement
        if (announcement.message.isNotBlank())
            setState { State.AnnouncementAvailable(announcement) }
        else
            onAnnouncementsNotFound()
    }

    private fun checkForUpdates() = launchInViewModelScope {
        val config = getConfigurationUseCase()
        val updateConfig = config.appUpdateConfig
        when (val updateStatus = updateHelper.getAppUpdateStatus(updateConfig)) {

            AppUpdateStatus.UpToDate -> {
                // no updates, proceed with app intro handling
                goToAppIntroOrMainApp()
            }

            is AppUpdateStatus.UpdateAvailable -> {
                handleUpdateAvailable(updateConfig, updateStatus)
            }
        }
    }

    private fun handleUpdateAvailable(
        updateConfig: AppUpdateConfig,
        updateStatus: AppUpdateStatus.UpdateAvailable
    ) {
        setState {
            State.UpdateAvailable(updateConfig, updateStatus)
        }
    }

    private fun goToAppIntroOrMainApp() = launchInViewModelScope {
        val config = getConfigurationUseCase()
        val appIntroConfig = config.appIntroConfig
        val introSeen = isIntroSeen()
        if (appIntroConfig.enabled && !introSeen) {
            setEffect(Effect.GoToAppIntro)
        } else {
            setEffect(Effect.GoToHome)
        }
    }

    private fun handleAnnouncementCtaClick(announcement: Announcement) {
        if (announcement.action.hasRedirectUrl) {
            setEffect(Effect.OpenWebUrl(announcement.action.url))
        } else {
            checkForUpdates()
        }
        sendAnalyticsEvent(AppLauncherAnalytics.getAnnouncementCtaEvent())
    }
}