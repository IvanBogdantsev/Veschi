package com.acerolla.impl

import com.acerolla.api.AuthStore
import com.acerolla.api.models.AuthErrorModel
import com.arkivanov.mvikotlin.core.store.Reducer

internal class AuthReducer : Reducer<AuthStore.State, AuthStoreFactory.Message> {

    override fun AuthStore.State.reduce(
        msg: AuthStoreFactory.Message,
    ) = when (msg) {
        is AuthStoreFactory.Message.SetLoading -> copy(
            isLoading = true,
            error = null
        )
        is AuthStoreFactory.Message.SetHttpError -> copy(
            isLoading = false,
            error = AuthErrorModel(msg.error.code, msg.error.errorBody, "Http exception")
        )
        is AuthStoreFactory.Message.SetNetworkError -> copy(
            isLoading = false,
            error = AuthErrorModel(msg = "Network exception")
        )
        is AuthStoreFactory.Message.SetSerializationError -> copy(
            isLoading = false,
            error = AuthErrorModel(msg = "Serialization exception")
        )
    }
}