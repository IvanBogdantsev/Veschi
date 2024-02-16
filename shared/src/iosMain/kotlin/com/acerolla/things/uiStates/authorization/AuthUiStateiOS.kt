package com.acerolla.things.uiStates.authorization

import com.acerolla.api.models.AuthErrorModel
import com.acerolla.common.ErrorResponse

data class AuthUiStateiOS(
    val successfullySigned: Boolean = false,
    val isLoading: Boolean = false,
    val error: AuthErrorModel<ErrorResponse>? = null
)
