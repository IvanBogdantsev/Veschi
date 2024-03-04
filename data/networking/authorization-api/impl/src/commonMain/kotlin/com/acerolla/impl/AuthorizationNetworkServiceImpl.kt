package com.acerolla.impl

import com.acerolla.add_thing_api.AuthorizationNetworkService
import com.acerolla.add_thing_api.models.Secret
import com.acerolla.add_thing_api.models.SignInDto
import com.acerolla.add_thing_api.models.SignUpDto
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse
import com.acerolla.common.TokenResponse
import com.acerolla.networking_utils.NetworkClientProvider
import com.acerolla.networking_utils.safeRequest
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

class AuthorizationNetworkServiceImpl(
    private val networkClientProvider: NetworkClientProvider
): AuthorizationNetworkService {

    private val httpClient by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        networkClientProvider.provideBaseHttpClient()
    }

    private val jwtHttpClient by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        networkClientProvider.provideJwtHttpClient()
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
            url(SIGN_UP)
        }
    }

    override suspend fun getSecret(): ApiResponse<Secret, ErrorResponse> {
        return jwtHttpClient.safeRequest {
            method = HttpMethod.Get
            url("get_secret")
        }
    }

    private companion object {

        const val SIGN_IN = "auth/sign_in"
        const val SIGN_UP = "auth/sign_up"
    }
}