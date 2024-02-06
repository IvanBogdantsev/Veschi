package com.acerolla.impl

import com.acerolla.api.AuthRepository
import com.acerolla.api.AuthStore
import com.acerolla.common.BaseExecutor

internal class MainExecutor(
    private val repository: AuthRepository,
) : BaseExecutor<AuthStore.Intent, Nothing, AuthStore.State, AuthStoreFactory.Message, Nothing>() {

    override suspend fun suspendExecuteIntent(
        intent: AuthStore.Intent,
        getState: () -> AuthStore.State,
    ) = when (intent) {
        is AuthStore.Intent.Load -> {

        }
    }
}
