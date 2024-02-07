package com.acerolla.things.uiStates.authorization

import com.acerolla.api.models.AuthErrorModel
import com.acerolla.common.ErrorResponse

data class AuthUiStateiOS(
    val signIn: Boolean = false,
    val signUp: Boolean = true,
    val forgotPassword: Boolean = false,
    val successfullySigned: Boolean = false,
    val isLoading: Boolean = false,
    val error: AuthErrorModel<ErrorResponse>? = null
)
