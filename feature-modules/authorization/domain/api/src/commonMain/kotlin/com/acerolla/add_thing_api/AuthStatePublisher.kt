package com.acerolla.add_thing_api

import com.acerolla.add_thing_api.models.SignInModel
import com.acerolla.add_thing_api.models.SignUpModel
import kotlinx.coroutines.flow.Flow

interface AuthStatePublisher {

    fun publishState(): Flow<AuthStore.State>

    fun signIn(model: SignInModel)

    fun signUp(model: SignUpModel)
}