package com.acerolla.impl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.acerolla.api.TokenManager
import com.acerolla.api.models.AccessToken
import com.acerolla.api.models.RefreshToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val tokenDatastore = "TokenDataStore"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = tokenDatastore)

class TokenManagerImpl(
    private val context: Context
): TokenManager {

    override fun getAccessToken(): Flow<AccessToken?> {
        return context.dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN_KEY]
        }.map(::toAccessToken)
    }

    override fun getRefreshToken(): Flow<RefreshToken?> {
        return context.dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN_KEY]
        }.map(::toRefreshToken)
    }

    override suspend fun saveAccessToken(accessToken: AccessToken) {
        accessToken.token?.let { token ->
            context.dataStore.edit { preferences ->
                preferences[ACCESS_TOKEN_KEY] = token
            }
        }
    }

    override suspend fun saveRefreshToken(refreshToken: RefreshToken) {
        refreshToken.token?.let { token ->
            context.dataStore.edit { preferences ->
                preferences[REFRESH_TOKEN_KEY] = token
            }
        }
    }

    override suspend fun deleteAccessToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN_KEY)
        }
    }

    override suspend fun deleteRefreshToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(REFRESH_TOKEN_KEY)
        }
    }

    private fun toAccessToken(token: String?): AccessToken = AccessToken(token)

    private fun toRefreshToken(token: String?): RefreshToken = RefreshToken(token)

    private companion object {

        val ACCESS_TOKEN_KEY = stringPreferencesKey("ACCESS_TOKEN")
        val REFRESH_TOKEN_KEY = stringPreferencesKey("REFRESH_TOKEN")
    }
}