package com.acerolla.data

import com.acerolla.api.AuthRepository
import com.acerolla.api.AuthStatusChecker
import com.acerolla.api.AuthorizationNetworkService
import com.acerolla.api.TokenManager
import com.acerolla.api.models.AccessToken
import com.acerolla.api.models.AuthStatus
import com.acerolla.api.models.RefreshToken
import com.acerolla.api.models.SignInModel
import com.acerolla.api.models.SignUpModel
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse
import com.acerolla.common.TokenResponse
import com.acerolla.data.mappers.toDto

class AuthRepositoryImpl(
    private val authorizationNetworkService: AuthorizationNetworkService,
    private val tokenManager: TokenManager,
    private val authStatusChecker: AuthStatusChecker
): AuthRepository {

    override suspend fun signUp(signUpModel: SignUpModel): ApiResponse<TokenResponse, ErrorResponse> =
        authorizationNetworkService.signUp(signUpModel.toDto())

    override suspend fun signIn(signInModel: SignInModel): ApiResponse<TokenResponse, ErrorResponse> =
        authorizationNetworkService.signIn(signInModel.toDto())

    override suspend fun saveTokens(tokens: TokenResponse) {
        tokenManager.saveAccessToken(AccessToken(tokens.accessToken))
        tokenManager.saveRefreshToken(RefreshToken(tokens.refreshToken))
    }

    override suspend fun markUserAuthorized() {
        authStatusChecker.setAuthStatus(AuthStatus((true)))
    }
}