package com.acerolla.api

import com.acerolla.api.models.AuthErrorModel
import com.acerolla.common.ErrorResponse
import com.arkivanov.mvikotlin.core.store.Store

interface AuthStore: Store<AuthStore.Intent, AuthStore.State, AuthStore.Label> {

    data class State(
        val isLoading: Boolean = false,
        val error: AuthErrorModel<ErrorResponse>? = null
    )

    data class Label(
        val toastString: String
    )

    sealed interface Intent {
        object Load: Intent
    }
}