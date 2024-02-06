package com.acerolla.impl

import com.acerolla.api.AuthorizationNetworkService
import com.acerolla.networking_utils.NetworkClientProvider
import com.acerolla.networking_utils.safeRequest
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.plugin
import io.ktor.client.request.headers
import io.ktor.client.request.url

class AuthorizationNetworkServiceImpl(
    private val networkClientProvider: NetworkClientProvider
): AuthorizationNetworkService {

    private val httpClient by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        networkClientProvider.provideBaseHttpClient()
    }

    override suspend fun signIn() {
        httpClient.plugin(HttpSend).intercept {  request ->
            val originalCall = execute(request)
            if (originalCall.response.status.value !in 100..399) {
                execute(request)
            } else {
                originalCall
            }
        }
        httpClient.safeRequest<String, Int> {
            url("")
        }
    }
}

//override suspend fun fetchTrainingSessions(): ApiResponse<List<TrainingSessionResponse>, ErrorResponse> =
//    httpClient.safeRequest { url(trainingsSessions) }