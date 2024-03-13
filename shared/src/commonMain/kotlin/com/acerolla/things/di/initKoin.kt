package com.acerolla.things.di

import com.acerolla.add_thing_domain_api.AddThingStore
import com.acerolla.add_thing_api.AddThingNetworkService
import com.acerolla.add_thing_api.AuthRepository
import com.acerolla.add_thing_api.AuthStatusChecker
import com.acerolla.add_thing_api.AuthStore
import com.acerolla.add_thing_api.AuthorizationNetworkService
import com.acerolla.add_thing_api.ProfileNetworkService
import com.acerolla.add_thing_api.StreetObjectsRepository
import com.acerolla.add_thing_api.ThingsNetworkService
import com.acerolla.add_thing_api.ThingsStore
import com.acerolla.add_thing_api.TokenManager
import com.acerolla.add_thing_api.models.StreetObject
import com.acerolla.add_thing_api.models.StreetObjectDto
import com.acerolla.add_thing_api.models.StreetObjectResponse
import com.acerolla.add_thing_api.models.StreetObjectsForCoordinate
import com.acerolla.add_thing_domain_api.AddThingsUseCase
import com.acerolla.common.mappers.BaseApiResponseMapper
import com.acerolla.data.AddThingsUseCaseImpl
import com.acerolla.data.AuthRepositoryImpl
import com.acerolla.data.ProfileRepositoryImpl
import com.acerolla.data.StreetObjectsRepositoryImpl
import com.acerolla.data.mappers.StreetObjectDtoToDomainModelMapper
import com.acerolla.data.mappers.StreetObjectResponseDtoToDomainModelMapper
import com.acerolla.impl.AddThingNetworkServiceImpl
import com.acerolla.impl.AddThingStoreFactory
import com.acerolla.impl.AuthStatusCheckerImpl
import com.acerolla.impl.AuthStoreFactory
import com.acerolla.impl.AuthorizationNetworkServiceImpl
import com.acerolla.impl.ProfileNetworkServiceImpl
import com.acerolla.impl.ProfileStoreFactory
import com.acerolla.impl.ThingsNetworkServiceImpl
import com.acerolla.impl.ThingsStoreFactory
import com.acerolla.impl.TokenManagerImpl
import com.acerolla.networking_utils.BaseNetworkHttpClientProvider
import com.acerolla.networking_utils.NetworkClientProvider
import com.acerolla.networking_utils.jwt.JwtAuthManager
import com.acerolla.networking_utils.jwt.JwtAuthManagerImpl
import com.acerolla.profile_domain_api.ProfileRepository
import com.acerolla.profile_domain_api.ProfileStore
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

    /** Add Thing Feature Dependencies */
    single<AddThingNetworkService> { AddThingNetworkServiceImpl(get()) }
    single<AddThingsUseCase> { AddThingsUseCaseImpl(get()) }
    factory<AddThingStore> {
        AddThingStoreFactory(
            storeFactory = get(),
            useCase = get()
        ).create()
    }
    factory<StoreFactory> {
        val logger = object : Logger {
            override fun log(text: String) {
                co.touchlab.kermit.Logger.withTag("AddThingStoreFactory").d(text)
            }
        }
        LoggingStoreFactory(DefaultStoreFactory(), logger = logger)
    }

    /** Profile Feature Dependencies */
    single<ProfileNetworkService> { ProfileNetworkServiceImpl(get()) }
    single<ProfileRepository> { ProfileRepositoryImpl() }
    factory<ProfileStore> {
        ProfileStoreFactory(
            storeFactory = get(),
            repository = get()
        ).create()
    }
    factory<StoreFactory> {
        val logger = object : Logger {
            override fun log(text: String) {
                co.touchlab.kermit.Logger.withTag("ProfileStoreFactory").d(text)
            }
        }
        LoggingStoreFactory(DefaultStoreFactory(), logger = logger)
    }

}