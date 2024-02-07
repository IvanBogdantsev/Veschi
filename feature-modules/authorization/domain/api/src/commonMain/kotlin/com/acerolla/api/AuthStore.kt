package com.acerolla.api

import com.acerolla.api.models.AuthErrorModel
import com.acerolla.api.models.SignInModel
import com.acerolla.api.models.SignUpModel
import com.acerolla.common.ErrorResponse
import com.arkivanov.mvikotlin.core.store.Store

interface AuthStore: Store<AuthStore.Intent, AuthStore.State, AuthStore.Label> {

    data class State(
        val moveToSignIn: Boolean = false,
        val moveToSignUp: Boolean = false,
        val moveToForgotPassword: Boolean = false,
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
        data object MoveToSignIn: Intent
        data object MoveToSignUp: Intent
    }
}