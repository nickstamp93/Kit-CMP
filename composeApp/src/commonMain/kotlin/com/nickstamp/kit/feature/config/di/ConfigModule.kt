package com.nickstamp.kit.feature.config.di

import com.nickstamp.kit.feature.config.data.datasource.local.ConfigurationLocalDataSource
import com.nickstamp.kit.feature.config.data.datasource.local.ConfigurationLocalDataSourceImpl
import com.nickstamp.kit.feature.config.data.datasource.remote.ConfigurationRemoteDataSource
import com.nickstamp.kit.feature.config.data.repository.ConfigurationRepositoryImpl
import com.nickstamp.kit.feature.config.data.service.ConfigurationService
import com.nickstamp.kit.feature.config.domain.repository.ConfigurationRepository
import com.nickstamp.kit.feature.config.domain.usecase.GetConfigurationUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val configModule = module {
    // Network Services - following the network layer pattern
    singleOf(::ConfigurationService)

    // Data Sources
    singleOf(::ConfigurationRemoteDataSource)
    singleOf(::ConfigurationLocalDataSourceImpl) bind ConfigurationLocalDataSource::class

    // Repository Implementation
    singleOf(::ConfigurationRepositoryImpl) bind ConfigurationRepository::class

    // Use Cases - Business Logic Layer
    singleOf(::GetConfigurationUseCase)
}