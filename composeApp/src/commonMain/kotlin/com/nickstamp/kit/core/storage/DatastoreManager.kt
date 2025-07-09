package com.nickstamp.kit.core.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

interface DatastoreManager {
    // String operations
    suspend fun saveString(key: String, value: String)
    suspend fun getString(key: String, defaultValue: String = ""): String
    fun getStringFlow(key: String, defaultValue: String = ""): Flow<String>
    
    // Int operations
    suspend fun saveInt(key: String, value: Int)
    suspend fun getInt(key: String, defaultValue: Int = 0): Int
    fun getIntFlow(key: String, defaultValue: Int = 0): Flow<Int>
    
    // Boolean operations
    suspend fun saveBoolean(key: String, value: Boolean)
    suspend fun getBoolean(key: String, defaultValue: Boolean = false): Boolean
    fun getBooleanFlow(key: String, defaultValue: Boolean = false): Flow<Boolean>
    
    // Long operations
    suspend fun saveLong(key: String, value: Long)
    suspend fun getLong(key: String, defaultValue: Long = 0L): Long
    fun getLongFlow(key: String, defaultValue: Long = 0L): Flow<Long>
    
    // Float operations
    suspend fun saveFloat(key: String, value: Float)
    suspend fun getFloat(key: String, defaultValue: Float = 0f): Float
    fun getFloatFlow(key: String, defaultValue: Float = 0f): Flow<Float>
    
    // Double operations
    suspend fun saveDouble(key: String, value: Double)
    suspend fun getDouble(key: String, defaultValue: Double = 0.0): Double
    fun getDoubleFlow(key: String, defaultValue: Double = 0.0): Flow<Double>
    
    // String Set operations
    suspend fun saveStringSet(key: String, value: Set<String>)
    suspend fun getStringSet(key: String, defaultValue: Set<String> = emptySet()): Set<String>
    fun getStringSetFlow(key: String, defaultValue: Set<String> = emptySet()): Flow<Set<String>>
    
    // Utility operations
    suspend fun remove(key: String)
    suspend fun clear()
    suspend fun contains(key: String): Boolean
    fun getAllPreferences(): Flow<Preferences>
}

class DatastoreManagerImpl(
    private val dataStore: DataStore<Preferences>
) : DatastoreManager {
    
    // String operations
    override suspend fun saveString(key: String, value: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }
    
    override suspend fun getString(key: String, defaultValue: String): String {
        return dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(key)] ?: defaultValue
        }.first()
    }
    
    override fun getStringFlow(key: String, defaultValue: String): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(key)] ?: defaultValue
        }
    }
    
    // Int operations
    override suspend fun saveInt(key: String, value: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(key)] = value
        }
    }
    
    override suspend fun getInt(key: String, defaultValue: Int): Int {
        return dataStore.data.map { preferences ->
            preferences[intPreferencesKey(key)] ?: defaultValue
        }.first()
    }
    
    override fun getIntFlow(key: String, defaultValue: Int): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[intPreferencesKey(key)] ?: defaultValue
        }
    }
    
    // Boolean operations
    override suspend fun saveBoolean(key: String, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }
    
    override suspend fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return dataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(key)] ?: defaultValue
        }.first()
    }
    
    override fun getBooleanFlow(key: String, defaultValue: Boolean): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(key)] ?: defaultValue
        }
    }
    
    // Long operations
    override suspend fun saveLong(key: String, value: Long) {
        dataStore.edit { preferences ->
            preferences[longPreferencesKey(key)] = value
        }
    }
    
    override suspend fun getLong(key: String, defaultValue: Long): Long {
        return dataStore.data.map { preferences ->
            preferences[longPreferencesKey(key)] ?: defaultValue
        }.first()
    }
    
    override fun getLongFlow(key: String, defaultValue: Long): Flow<Long> {
        return dataStore.data.map { preferences ->
            preferences[longPreferencesKey(key)] ?: defaultValue
        }
    }
    
    // Float operations
    override suspend fun saveFloat(key: String, value: Float) {
        dataStore.edit { preferences ->
            preferences[floatPreferencesKey(key)] = value
        }
    }
    
    override suspend fun getFloat(key: String, defaultValue: Float): Float {
        return dataStore.data.map { preferences ->
            preferences[floatPreferencesKey(key)] ?: defaultValue
        }.first()
    }
    
    override fun getFloatFlow(key: String, defaultValue: Float): Flow<Float> {
        return dataStore.data.map { preferences ->
            preferences[floatPreferencesKey(key)] ?: defaultValue
        }
    }
    
    // Double operations
    override suspend fun saveDouble(key: String, value: Double) {
        dataStore.edit { preferences ->
            preferences[doublePreferencesKey(key)] = value
        }
    }
    
    override suspend fun getDouble(key: String, defaultValue: Double): Double {
        return dataStore.data.map { preferences ->
            preferences[doublePreferencesKey(key)] ?: defaultValue
        }.first()
    }
    
    override fun getDoubleFlow(key: String, defaultValue: Double): Flow<Double> {
        return dataStore.data.map { preferences ->
            preferences[doublePreferencesKey(key)] ?: defaultValue
        }
    }
    
    // String Set operations
    override suspend fun saveStringSet(key: String, value: Set<String>) {
        dataStore.edit { preferences ->
            preferences[stringSetPreferencesKey(key)] = value
        }
    }
    
    override suspend fun getStringSet(key: String, defaultValue: Set<String>): Set<String> {
        return dataStore.data.map { preferences ->
            preferences[stringSetPreferencesKey(key)] ?: defaultValue
        }.first()
    }
    
    override fun getStringSetFlow(key: String, defaultValue: Set<String>): Flow<Set<String>> {
        return dataStore.data.map { preferences ->
            preferences[stringSetPreferencesKey(key)] ?: defaultValue
        }
    }
    
    // Utility operations
    override suspend fun remove(key: String) {
        dataStore.edit { preferences ->
            // Try to remove all possible types for this key
            preferences.remove(stringPreferencesKey(key))
            preferences.remove(intPreferencesKey(key))
            preferences.remove(booleanPreferencesKey(key))
            preferences.remove(longPreferencesKey(key))
            preferences.remove(floatPreferencesKey(key))
            preferences.remove(doublePreferencesKey(key))
            preferences.remove(stringSetPreferencesKey(key))
        }
    }
    
    override suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
    
    override suspend fun contains(key: String): Boolean {
        return dataStore.data.map { preferences ->
            preferences.contains(stringPreferencesKey(key)) ||
            preferences.contains(intPreferencesKey(key)) ||
            preferences.contains(booleanPreferencesKey(key)) ||
            preferences.contains(longPreferencesKey(key)) ||
            preferences.contains(floatPreferencesKey(key)) ||
            preferences.contains(doublePreferencesKey(key)) ||
            preferences.contains(stringSetPreferencesKey(key))
        }.first()
    }
    
    override fun getAllPreferences(): Flow<Preferences> {
        return dataStore.data
    }
}