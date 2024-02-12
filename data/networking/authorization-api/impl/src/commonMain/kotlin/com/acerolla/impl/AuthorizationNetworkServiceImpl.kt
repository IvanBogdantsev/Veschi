package com.acerolla.impl

import com.acerolla.api.AuthorizationNetworkService
import com.acerolla.api.models.SignInDto
import com.acerolla.api.models.SignUpDto
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse
import com.acerolla.common.TokenResponse
import com.acerolla.networking_utils.NetworkClientProvider
import com.acerolla.networking_utils.safeRequest
import io.ktor.client.request.accept
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class AuthorizationNetworkServiceImpl(
    private val networkClientProvider: NetworkClientProvider
): AuthorizationNetworkService {

    private val httpClient by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        networkClientProvider.provideBaseHttpClient()
    }

    override suspend fun signIn(dto: SignInDto): ApiResponse<TokenResponse, ErrorResponse> {
        return httpClient.safeRequest {
            method = HttpMethod.Post
            setBody(dto)
            url(SIGN_IN)
        }
    }

    override suspend fun signUp(dto: SignUpDto): ApiResponse<TokenResponse, ErrorResponse> {
        return httpClient.safeRequest {
            method = HttpMethod.Post
            setBody(dto)
            url(SIGN_UP) }
    }

    private companion object {

        const val SIGN_IN = "auth/sign_in"
        const val SIGN_UP = "auth/sign_up"
    }
}