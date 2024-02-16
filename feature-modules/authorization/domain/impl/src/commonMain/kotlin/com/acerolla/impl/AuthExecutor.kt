package com.acerolla.impl

import com.acerolla.api.AuthRepository
import com.acerolla.api.AuthStore
import com.acerolla.api.models.SignInModel
import com.acerolla.api.models.SignUpModel
import com.acerolla.common.ApiResponse
import com.acerolla.common.BaseExecutor

internal class MainExecutor(
    private val repository: AuthRepository,
) : BaseExecutor<AuthStore.Intent, Nothing, AuthStore.State, AuthStoreFactory.Message, Nothing>() {

    override suspend fun suspendExecuteIntent(
        intent: AuthStore.Intent,
        getState: () -> AuthStore.State,
    ) = when (intent) {
        is AuthStore.Intent.SignUp -> signUp(intent.model)
        is AuthStore.Intent.SignIn -> signIn(intent.model)
    }

    private suspend fun signIn(model: SignInModel) {
        dispatch(AuthStoreFactory.Message.SetLoading)
        when(val response = repository.signIn(model)) {
            is ApiResponse.Success -> {
                repository.saveTokens(response.body)
                dispatch(AuthStoreFactory.Message.SuccessfullySigned(response.body))
            }
            is ApiResponse.Error.HttpError -> {
                dispatch(AuthStoreFactory.Message.SetHttpError(response))
            }
            is ApiResponse.Error.NetworkError -> {
                dispatch(AuthStoreFactory.Message.SetNetworkError)
            }
            is ApiResponse.Error.SerializationError -> {
                dispatch(AuthStoreFactory.Message.SetSerializationError)
            }
            else -> throw IllegalStateException("No such response type")
        }
    }

    private suspend fun signUp(model: SignUpModel) {
        dispatch(AuthStoreFactory.Message.SetLoading)
        when(val response = repository.signUp(model)) {
            is ApiResponse.Success -> {
                repository.saveTokens(response.body)
                dispatch(AuthStoreFactory.Message.SuccessfullySigned(response.body))
            }
            is ApiResponse.Error.HttpError -> {
                dispatch(AuthStoreFactory.Message.SetHttpError(response))
            }
            is ApiResponse.Error.NetworkError -> {
                dispatch(AuthStoreFactory.Message.SetNetworkError)
            }
            is ApiResponse.Error.SerializationError -> {
                dispatch(AuthStoreFactory.Message.SetSerializationError)
            }
            is ApiResponse.Error.TimeoutError -> {
                dispatch(AuthStoreFactory.Message.SetNetworkError)
            }
            else -> throw IllegalStateException("No such response type")
        }
    }
}
