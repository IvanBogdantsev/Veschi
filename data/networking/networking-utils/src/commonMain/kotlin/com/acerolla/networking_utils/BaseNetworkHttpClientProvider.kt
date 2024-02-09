package com.acerolla.networking_utils

import com.acerolla.api.TokenManager
import com.acerolla.api.models.AccessToken
import com.acerolla.api.models.RefreshToken
import com.acerolla.common.ApiResponse
import com.acerolla.common.TokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.headers
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.ContentType.Application.ProtoBuf
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import io.ktor.serialization.kotlinx.serialization
import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json

private const val AUTHORIZATION_HEADER = "Authorization"

class BaseNetworkHttpClientProvider(
    private val tokenManager: TokenManager
): NetworkClientProvider {

    private val baseHttpClient: HttpClient = HttpClient {
        install(HttpTimeout) {
            requestTimeoutMillis = 10_000
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
//                isLenient = true
                useAlternativeNames = false
            })
//            serialization(ContentType.Application.Json, BinaryFormat)
//            register(
//                ContentType.Text.Any, KotlinxSerializationConverter(
//                    Json {
//                        prettyPrint = true
//                        isLenient = true
//                        ignoreUnknownKeys = true
//                    }
//                )
//            )
        }
        install(Logging) {
            level = LogLevel.ALL
        }
        defaultRequest {
//            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
            url {
//                header(HttpHeaders.ContentType, ContentType.Application.Json)
                protocol = URLProtocol.HTTP
                host = MOCK_URL
                port = MOCK_PORT
            }
        }
    }

    override fun provideBaseHttpClient(): HttpClient = baseHttpClient

    // TODO: Need refactoring
    override fun provideJwtHttpClient(): HttpClient {
        val jwtClient = baseHttpClient
        jwtClient.plugin(HttpSend).intercept { request ->
            val originalCall = execute(request)
            if (originalCall.response.status == HttpStatusCode.Unauthorized) {
                when(val newTokenResponse = baseHttpClient.safeRequest<TokenResponse, Int> { url(refreshTokenUrl) }) {
                    is ApiResponse.Success -> {
                        tokenManager.saveAccessToken(AccessToken(newTokenResponse.body.accessToken))
                        tokenManager.saveRefreshToken(RefreshToken(newTokenResponse.body.refreshToken))
                        request.headers {
                            append(AUTHORIZATION_HEADER, "Bearer ${newTokenResponse.body.accessToken}")
                        }
                        execute(request)
                    }
                    else -> {}
                }
                throw RuntimeException("Error while trying to refresh jwt token")
            } else {
                originalCall
            }
        }
        return jwtClient
    }

    // TODO: Need to refactor and move mock build flag to BuildConfig
    private companion object {
        const val MOCK_URL = "45.12.19.184"
        const val MOCK_PORT = 9091
        const val refreshTokenUrl = "/auth/refresh"
    }
}