package com.acerolla.networking_utils.jwt

import com.acerolla.api.TokenManager
import com.acerolla.api.models.AccessToken
import com.acerolla.api.models.RefreshToken
import com.acerolla.common.TokenResponse

class JwtAuthManagerImpl(
    private val tokenManager: TokenManager
): JwtAuthManager {

    override suspend fun saveNewToken(tokenResponse: TokenResponse) {
        tokenManager.saveRefreshToken(RefreshToken(tokenResponse.accessToken))
        tokenManager.saveAccessToken(AccessToken(tokenResponse.refreshToken))
    }
}