package com.acerolla.ui_android

import com.acerolla.api.models.AuthErrorModel
import com.acerolla.common.ErrorResponse

data class UiState(
    val signIn: Boolean = false,
    val signUp: Boolean = true,
    val forgotPassword: Boolean = false,
    val successfullySigned: Boolean = false,
    val isLoading: Boolean = false,
    val error: AuthErrorModel<ErrorResponse>? = null
) {

    fun isErrorOccurred(): Boolean = error != null
}