package com.nickstamp.kit.core.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

private val android.content.Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

actual fun createDataStore(): DataStore<Preferences> {
    return object : KoinComponent {
        val context: android.content.Context = get()
    }.context.dataStore
}