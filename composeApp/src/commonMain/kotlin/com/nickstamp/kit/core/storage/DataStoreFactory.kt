package com.nickstamp.kit.core.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

/**
 * Factory function for creating DataStore instances across platforms
 */
expect fun createDataStore(): DataStore<Preferences>