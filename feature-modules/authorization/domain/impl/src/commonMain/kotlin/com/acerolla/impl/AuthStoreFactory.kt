package com.acerolla.impl

import com.acerolla.api.AuthRepository
import com.acerolla.api.AuthStore
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory

class AuthStoreFactory(
    private val storeFactory: StoreFactory,
    private val repository: AuthRepository
) {

    fun create(): AuthStore = object: AuthStore,
        Store<AuthStore.Intent, AuthStore.State, AuthStore.Label> by storeFactory.create(
            name = AuthStore::class.simpleName,
            initialState = AuthStore.State(),
            bootstrapper = null,
            executorFactory = {
                MainExecutor(repository)
            },
            reducer = AuthReducer(),
        ) {}


    sealed interface Message {

        object SetLoading: Message
        data class SetHttpError(val error: ApiResponse.Error.HttpError<ErrorResponse>): Message
        object SetNetworkError: Message
        object SetSerializationError: Message
    }
}