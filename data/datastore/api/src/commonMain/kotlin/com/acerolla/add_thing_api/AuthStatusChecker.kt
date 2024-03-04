package com.acerolla.add_thing_api

import com.acerolla.add_thing_api.models.AuthStatus

interface AuthStatusChecker {

    suspend fun setAuthStatus(status: AuthStatus)

    suspend fun isUserAuthorized(): AuthStatus?
}