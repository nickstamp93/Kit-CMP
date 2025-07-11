package com.nickstamp.kit.core.di

import com.nickstamp.kit.core.helpers.DateTimeHelper
import com.nickstamp.kit.core.helpers.impl.DateTimeHelperImpl
import com.nickstamp.kit.core.network.ApiService
import com.nickstamp.kit.core.network.httpClient
import com.nickstamp.kit.core.storage.DatastoreManager
import com.nickstamp.kit.core.storage.DatastoreManagerImpl
import com.nickstamp.kit.core.storage.createDataStore
import com.nickstamp.kit.core.storage.example.DefaultDatastoreManager
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreModule = module {
    single<HttpClient> { httpClient() }
    singleOf(::ApiService)
    single { createDataStore() }
    singleOf(::DatastoreManagerImpl) bind DatastoreManager::class
    singleOf(::DefaultDatastoreManager)
    singleOf(::DateTimeHelperImpl) bind DateTimeHelper::class
}