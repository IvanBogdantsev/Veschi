package com.acerolla.things.di

import com.acerolla.api.AuthRepository
import com.acerolla.api.AuthStore
import com.acerolla.api.AuthorizationNetworkService
import com.acerolla.api.TokenManager
import com.acerolla.data.AuthRepositoryImpl
import com.acerolla.impl.AuthStoreFactory
import com.acerolla.impl.AuthorizationNetworkServiceImpl
import com.acerolla.impl.TokenManagerImpl
import com.acerolla.networking_utils.BaseNetworkHttpClientProvider
import com.acerolla.networking_utils.NetworkClientProvider
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

    single<TokenManager> { TokenManagerImpl(get()) }
    single<NetworkClientProvider> { BaseNetworkHttpClientProvider(get()) }

    /** Authorization Feature Dependencies */
    single<AuthorizationNetworkService> { AuthorizationNetworkServiceImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    factory<AuthStore> {
        AuthStoreFactory(
            storeFactory = get(),
            repository = get(),
        ).create()
    }
    factory<StoreFactory> {
        val logger = object : Logger {
            override fun log(text: String) {
                // TODO: add Logger
            }
        }
        LoggingStoreFactory(DefaultStoreFactory(), logger = logger)
    }
}
