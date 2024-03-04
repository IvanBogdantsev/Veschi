package com.acerolla.add_thing_api

import com.acerolla.add_thing_api.models.AccessToken
import com.acerolla.add_thing_api.models.RefreshToken
import kotlinx.coroutines.flow.Flow

interface TokenManager {

    fun getAccessToken(): Flow<AccessToken?>

    fun getRefreshToken(): Flow<RefreshToken?>

    suspend fun saveAccessToken(accessToken: AccessToken)

    suspend fun saveRefreshToken(refreshToken: RefreshToken)

    suspend fun deleteAccessToken()

    suspend fun deleteRefreshToken()
}