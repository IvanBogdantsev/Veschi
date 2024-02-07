package com.acerolla.impl

import com.acerolla.api.AuthStatePublisher
import com.acerolla.api.AuthStore
import com.acerolla.api.models.SignInModel
import com.acerolla.api.models.SignUpModel
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.flow.Flow

class AuthStatePublisherImpl(
    private val store: AuthStore,
): AuthStatePublisher {

    override fun publishState(): Flow<AuthStore.State> = store.states

    override fun signIn(model: SignInModel) = store.accept(AuthStore.Intent.SignIn(model))

    override fun signUp(model: SignUpModel) = store.accept(AuthStore.Intent.SignUp(model))

    override fun moveToSignIn() = store.accept(AuthStore.Intent.MoveToSignIn)

    override fun moveToSignUp() = store.accept(AuthStore.Intent.MoveToSignUp)

    override fun moveToForgotPassword() = store.accept(AuthStore.Intent.MoveToForgotPassword)
}