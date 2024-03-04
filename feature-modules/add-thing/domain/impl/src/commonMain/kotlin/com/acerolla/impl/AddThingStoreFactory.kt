package com.acerolla.impl

import com.acerolla.add_thing_domain_api.AddThingStore
import com.acerolla.add_thing_domain_api.AddThingsUseCase
import com.acerolla.add_thing_domain_api.model.AddThingErrorModel
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory

class AddThingStoreFactory(
    private val storeFactory: StoreFactory,
    private val useCase: AddThingsUseCase
) {

    fun create(): AddThingStore = object: AddThingStore,
        Store<AddThingStore.Intent, AddThingStore.State, AddThingStore.Label> by storeFactory.create(
            name = AddThingStore::class.simpleName,
            initialState = AddThingStore.State(),
            bootstrapper = null,
            executorFactory = {
                AddThingsExecutor(useCase)
            },
            reducer = AddThingsReducer(),
        ) {}


    sealed interface Message {
        data object SuccessfullyAdded: Message
        data object SetLoading: Message
        data class SetHttpError(val error: ApiResponse.Error.HttpError<ErrorResponse>): Message
        data object SetNetworkError: Message
        data object SetSerializationError: Message
    }
}