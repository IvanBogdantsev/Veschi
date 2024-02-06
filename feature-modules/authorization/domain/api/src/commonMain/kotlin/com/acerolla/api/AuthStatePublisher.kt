package com.acerolla.api

import kotlinx.coroutines.flow.Flow

interface AuthStatePublisher {

    fun publishState(): Flow<AuthStore.State>

    fun loadTrainingSessions()
}