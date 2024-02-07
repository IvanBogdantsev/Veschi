package com.acerolla.api

import com.acerolla.api.models.SignInModel
import com.acerolla.api.models.SignUpModel
import kotlinx.coroutines.flow.Flow

interface AuthStatePublisher {

    fun publishState(): Flow<AuthStore.State>

    fun signIn(model: SignInModel)

    fun signUp(model: SignUpModel)
}