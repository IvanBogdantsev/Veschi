package com.acerolla.api

import com.acerolla.api.models.AuthStatus

interface AuthStatusChecker {

    suspend fun setAuthStatus(status: AuthStatus)

    suspend fun isUserAuthorized(): AuthStatus?
}