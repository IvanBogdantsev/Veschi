package com.acerolla.impl

import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse
import com.acerolla.common.TokenResponse
import com.acerolla.profile_domain_api.ProfileRepository
import com.acerolla.profile_domain_api.ProfileStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory

class ProfileStoreFactory(
    private val storeFactory: StoreFactory,
    private val repository: ProfileRepository
) {

    fun create(): ProfileStore = object: ProfileStore,
        Store<ProfileStore.Intent, ProfileStore.State, ProfileStore.Label> by storeFactory.create(
            name = ProfileStore::class.simpleName,
            initialState = ProfileStore.State(),
            bootstrapper = null,
            executorFactory = {
                ProfileExecutor(repository)
            },
            reducer = ProfileReducer(),
        ) {}


    sealed interface Message {
        data class SuccessfullySigned(val response: TokenResponse): Message
        data object SetLoading: Message
        data class SetHttpError(val error: ApiResponse.Error.HttpError<ErrorResponse>): Message
        data object SetNetworkError: Message
        data object SetSerializationError: Message
    }
}