package com.acerolla.impl

import com.acerolla.api.AuthStatePublisher
import com.acerolla.api.AuthStore
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.flow.Flow

class AuthStatePublisherImpl(
    private val store: AuthStore,
): AuthStatePublisher {

    override fun publishState(): Flow<AuthStore.State> = store.states

    override fun loadTrainingSessions() = store.accept(AuthStore.Intent.Load)
}