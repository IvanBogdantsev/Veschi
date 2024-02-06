package com.acerolla.networking_utils.jwt

import com.acerolla.common.TokenResponse

interface JwtAuthManager {

    suspend fun saveNewToken(tokenResponse: TokenResponse)
}