package com.acerolla.things.di

import com.acerolla.api.AuthRepository
import com.acerolla.api.AuthStatusChecker
import com.acerolla.api.AuthStore
import com.acerolla.api.AuthorizationNetworkService
import com.acerolla.api.StreetObjectsRepository
import com.acerolla.api.ThingsNetworkService
import com.acerolla.api.ThingsStore
import com.acerolla.api.TokenManager
import com.acerolla.api.models.StreetObject
import com.acerolla.api.models.StreetObjectDto
import com.acerolla.api.models.StreetObjectResponse
import com.acerolla.api.models.StreetObjectsForCoordinate
import com.acerolla.common.mappers.BaseApiResponseMapper
import com.acerolla.data.AuthRepositoryImpl
import com.acerolla.data.StreetObjectsRepositoryImpl
import com.acerolla.data.mappers.StreetObjectDtoToDomainModelMapper
import com.acerolla.data.mappers.StreetObjectResponseDtoToDomainModelMapper
import com.acerolla.impl.AuthStatusCheckerImpl
import com.acerolla.impl.AuthStoreFactory
import com.acerolla.impl.AuthorizationNetworkServiceImpl
import com.acerolla.impl.ThingsNetworkServiceImpl
import com.acerolla.impl.ThingsStoreFactory
import com.acerolla.impl.TokenManagerImpl
import com.acerolla.networking_utils.BaseNetworkHttpClientProvider
import com.acerolla.networking_utils.NetworkClientProvider
import com.acerolla.networking_utils.jwt.JwtAuthManager
import com.acerolla.networking_utils.jwt.JwtAuthManagerImpl
import com.acerolla.things.platformModule
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.logger.Logger
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(additionalModules: List<Module>): KoinApplication {
    return startKoin {
        modules(additionalModules + platformModule() + coreModule())
    }
}

private fun coreModule() = module {

    /** Core dependencies */
    single<NetworkClientProvider> { BaseNetworkHttpClientProvider(get()) }
    single<JwtAuthManager> { JwtAuthManagerImpl(get()) }

    /** Datastore */
    single<TokenManager> { TokenManagerImpl(get()) }
    single<AuthStatusChecker> { AuthStatusCheckerImpl(get()) }

    /** Authorization Feature Dependencies */
    single<AuthorizationNetworkService> { AuthorizationNetworkServiceImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }
    factory<AuthStore> {
        AuthStoreFactory(
            storeFactory = get(),
            repository = get(),
        ).create()
    }
    factory<StoreFactory> {
        val logger = object : Logger {
            override fun log(text: String) {
                co.touchlab.kermit.Logger.withTag("AuthStoreFactory").d(text)
            }
        }
        LoggingStoreFactory(DefaultStoreFactory(), logger = logger)
    }

    /** Things Feature Dependencies */
    single<ThingsNetworkService> { ThingsNetworkServiceImpl(get()) }
    single<BaseApiResponseMapper<StreetObjectResponse, StreetObjectsForCoordinate>> { StreetObjectResponseDtoToDomainModelMapper() }
    single<BaseApiResponseMapper<StreetObjectDto, StreetObject>> { StreetObjectDtoToDomainModelMapper() }
    single<StreetObjectsRepository> { StreetObjectsRepositoryImpl(get(), get(), get()) }
    factory<ThingsStore> {
        ThingsStoreFactory(
            storeFactory = get(),
            repository = get()
        ).create()
    }
    factory<StoreFactory> {
        val logger = object : Logger {
            override fun log(text: String) {
                co.touchlab.kermit.Logger.withTag("ThingsStoreFactory").d(text)
            }
        }
        LoggingStoreFactory(DefaultStoreFactory(), logger = logger)
    }
}