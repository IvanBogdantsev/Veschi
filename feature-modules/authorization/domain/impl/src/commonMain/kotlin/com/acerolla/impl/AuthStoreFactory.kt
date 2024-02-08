package com.acerolla.impl

import com.acerolla.api.AuthRepository
import com.acerolla.api.AuthStore
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse
import com.acerolla.common.TokenResponse
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
        data object MoveToSignIn: Message
        data object MoveToSignUp: Message
        data object MoveToForgotPassword: Message
        data class SuccessfullySigned(val response: TokenResponse): Message
        data object SetLoading: Message
        data class SetHttpError(val error: ApiResponse.Error.HttpError<ErrorResponse>): Message
        data object SetNetworkError: Message
        data object SetSerializationError: Message
    }
}