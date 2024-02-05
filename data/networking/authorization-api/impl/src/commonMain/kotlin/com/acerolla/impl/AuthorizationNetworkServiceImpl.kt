package com.acerolla.impl

import com.acerolla.api.AuthorizationNetworkService
import com.acerolla.networking_utils.NetworkClientProvider

class AuthorizationNetworkServiceImpl(
    private val networkClientProvider: NetworkClientProvider
): AuthorizationNetworkService {

    private val httpClient by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        networkClientProvider.provideBaseHttpClient()
    }


}