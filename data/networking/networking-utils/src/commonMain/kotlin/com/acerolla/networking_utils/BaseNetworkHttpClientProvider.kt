package com.acerolla.networking_utils

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class BaseNetworkHttpClientProvider: NetworkClientProvider {

    private val baseHttpClient: HttpClient = HttpClient {
        install(HttpTimeout) {
            requestTimeoutMillis = 10_000
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTP
                host = MOCK_URL
                port = MOCK_PORT
            }
        }
    }

    override fun provideBaseHttpClient(): HttpClient = baseHttpClient

    // TODO: Need to refactor and move mock build flag to BuildConfig
    private companion object {

        const val MOCK_URL = "45.12.19.184"
        const val MOCK_PORT = 90
    }
}