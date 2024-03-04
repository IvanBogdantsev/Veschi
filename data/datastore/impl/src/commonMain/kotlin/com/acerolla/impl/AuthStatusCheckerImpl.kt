package com.acerolla.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.acerolla.add_thing_api.AuthStatusChecker
import com.acerolla.add_thing_api.models.AuthStatus
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class AuthStatusCheckerImpl(
    private val dataStore: DataStore<Preferences>
): AuthStatusChecker {

    override suspend fun setAuthStatus(status: AuthStatus) {
        dataStore.edit { preferences ->
            preferences[AUTH_STATUS_KEY] = status.authorized ?: true
        }
    }

    override suspend fun isUserAuthorized(): AuthStatus? {
        return dataStore.data.map { preferences ->
            AuthStatus(preferences[AUTH_STATUS_KEY])
        }.firstOrNull()
    }

    private companion object {

        val AUTH_STATUS_KEY = booleanPreferencesKey("AUTH_STATUS_KEY")
    }
}