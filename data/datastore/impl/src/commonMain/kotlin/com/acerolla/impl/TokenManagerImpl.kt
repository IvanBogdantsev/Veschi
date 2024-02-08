package com.acerolla.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.acerolla.api.TokenManager
import com.acerolla.api.models.AccessToken
import com.acerolla.api.models.RefreshToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenManagerImpl(
    private val dataStore: DataStore<Preferences>
): TokenManager {

    override fun getAccessToken(): Flow<AccessToken?> = dataStore.data.map { preferences ->
        AccessToken(preferences[ACCESS_TOKEN_KEY])
    }

    override fun getRefreshToken(): Flow<RefreshToken?> = dataStore.data.map { preferences ->
        RefreshToken(preferences[REFRESH_TOKEN_KEY])
    }

    override suspend fun saveAccessToken(accessToken: AccessToken) {
        deleteAccessToken()
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = accessToken.token ?: ""
        }
    }

    override suspend fun saveRefreshToken(refreshToken: RefreshToken) {
        deleteRefreshToken()
        dataStore.edit { preferences ->
            preferences[REFRESH_TOKEN_KEY] = refreshToken.token ?: ""
        }
    }

    override suspend fun deleteAccessToken() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN_KEY)
        }
    }

    override suspend fun deleteRefreshToken() {
        dataStore.edit { preferences ->
            preferences.remove(REFRESH_TOKEN_KEY)
        }
    }

    private companion object {

        val ACCESS_TOKEN_KEY = stringPreferencesKey("ACCESS_TOKEN")
        val REFRESH_TOKEN_KEY = stringPreferencesKey("REFRESH_TOKEN")
    }
}