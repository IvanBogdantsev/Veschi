package com.acerolla.networking_utils

import com.acerolla.api.TokenManager
import com.acerolla.api.models.AccessToken
import com.acerolla.api.models.RefreshToken
import com.acerolla.common.TokenResponse
import com.acerolla.networking_utils.jwt.RefreshTokenDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json

private const val AUTHORIZATION_HEADER = "Authorization"

class BaseNetworkHttpClientProvider(
    private val tokenManager: TokenManager
): NetworkClientProvider {

    private val baseHttpClient: HttpClient = HttpClient {
        install(HttpTimeout) {
            requestTimeoutMillis = 20_000
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
                encodeDefaults = true
            })
        }
        install(Logging) {
            level = LogLevel.ALL
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            url {
                protocol = URLProtocol.HTTP
                host = MOCK_URL
                port = MOCK_PORT
            }
        }
    }

    override fun provideBaseHttpClient(): HttpClient = baseHttpClient

    override fun provideJwtHttpClient(): HttpClient {
        val jwtClient = baseHttpClient
        jwtClient.plugin(HttpSend).intercept { request ->
            val accessToken = tokenManager.getAccessToken().first()?.token
            request.headers {
                append(AUTHORIZATION_HEADER, "Bearer $accessToken")
            }
            val originalCall = execute(request)
            if (originalCall.response.status == HttpStatusCode.Unauthorized) {
                val newTokens = refreshToken()
                tokenManager.saveAccessToken(AccessToken(newTokens.accessToken))
                tokenManager.saveRefreshToken(RefreshToken(newTokens.refreshToken))
                request.headers {
                    append(AUTHORIZATION_HEADER, "Bearer ${newTokens.accessToken}")
                }
                execute(request)
            } else {
                originalCall
            }
        }
        return jwtClient
    }

    private suspend fun refreshToken(): TokenResponse {
        val refreshToken = tokenManager.getRefreshToken().first()?.token
        val newTokenResponse = baseHttpClient.post {
            setBody(RefreshTokenDto(refreshToken))
            url(refreshTokenUrl)
        }
        if (newTokenResponse.status == HttpStatusCode.OK) {
            return newTokenResponse.body<TokenResponse>()
        } else {
            throw RuntimeException("Error while trying to refresh jwt token")
        }
    }

    // TODO: Need to refactor and move mock build flag to BuildConfig
    private companion object {
        const val MOCK_URL = "45.12.19.184"
        const val MOCK_PORT = 9091
        const val refreshTokenUrl = "/auth/refresh"
    }
}