package com.acerolla.add_thing_api

import com.acerolla.add_thing_api.models.AuthErrorModel
import com.acerolla.add_thing_api.models.SignInModel
import com.acerolla.add_thing_api.models.SignUpModel
import com.acerolla.common.ErrorResponse
import com.arkivanov.mvikotlin.core.store.Store

interface AuthStore: Store<AuthStore.Intent, AuthStore.State, AuthStore.Label> {

    data class State(
        val isSuccessfullySigned: Boolean = false,
        val isLoading: Boolean = false,
        val error: AuthErrorModel<ErrorResponse>? = null
    )

    data class Label(
        val toastString: String
    )

    sealed interface Intent {
        data class SignIn(val model: SignInModel): Intent
        data class SignUp(val model: SignUpModel): Intent
    }
}