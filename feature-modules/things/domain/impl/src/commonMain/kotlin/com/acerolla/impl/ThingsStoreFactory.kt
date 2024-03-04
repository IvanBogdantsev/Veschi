package com.acerolla.impl

import com.acerolla.add_thing_api.StreetObjectsRepository
import com.acerolla.add_thing_api.ThingsStore
import com.acerolla.add_thing_api.models.StreetObjectsForCoordinate
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory

class ThingsStoreFactory(
    private val storeFactory: StoreFactory,
    private val repository: StreetObjectsRepository
) {

    fun create(): ThingsStore = object: ThingsStore,
        Store<ThingsStore.Intent, ThingsStore.State, ThingsStore.Label> by storeFactory.create(
            name = ThingsStore::class.simpleName,
            initialState = ThingsStore.State(),
            bootstrapper = null,
            executorFactory = {
                ThingsExecutor(repository)
            },
            reducer = ThingsReducer(),
        ) {}


    sealed interface Message {
        data class GotStreetObjects(val obj: StreetObjectsForCoordinate): Message
        data object SetLoading: Message
        data class SetHttpError(val error: ApiResponse.Error.HttpError<ErrorResponse>): Message
        data object SetNetworkError: Message
        data object SetSerializationError: Message
    }
}