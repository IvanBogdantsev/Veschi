package com.acerolla.ui_android

import com.acerolla.add_thing_api.models.AuthErrorModel
import com.acerolla.common.ErrorResponse

data class UiState(
    val successfullySigned: Boolean = false,
    val isLoading: Boolean = false,
    val error: AuthErrorModel<ErrorResponse>? = null
) {

    fun isErrorOccurred(): Boolean = error != null
}