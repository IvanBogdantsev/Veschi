package com.acerolla.data

import com.acerolla.add_thing_api.AuthRepository
import com.acerolla.add_thing_api.AuthStatusChecker
import com.acerolla.add_thing_api.AuthorizationNetworkService
import com.acerolla.add_thing_api.TokenManager
import com.acerolla.add_thing_api.models.AccessToken
import com.acerolla.add_thing_api.models.AuthStatus
import com.acerolla.add_thing_api.models.RefreshToken
import com.acerolla.add_thing_api.models.SignInModel
import com.acerolla.add_thing_api.models.SignUpModel
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