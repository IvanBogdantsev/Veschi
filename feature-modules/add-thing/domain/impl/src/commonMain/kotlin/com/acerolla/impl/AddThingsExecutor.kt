package com.acerolla.impl

import com.acerolla.add_thing_domain_api.AddThingStore
import com.acerolla.add_thing_domain_api.AddThingsUseCase
import com.acerolla.add_thing_domain_api.model.NewStreetObject
import com.acerolla.common.ApiResponse
import com.acerolla.common.BaseExecutor

class AddThingsExecutor(
    private val useCase: AddThingsUseCase
): BaseExecutor<AddThingStore.Intent, Nothing, AddThingStore.State, AddThingStoreFactory.Message, Nothing>() {

    override suspend fun suspendExecuteIntent(
        intent: AddThingStore.Intent,
        getState: () -> AddThingStore.State,
    ) = when (intent) {
        is AddThingStore.Intent.AddNewStreetObject -> {
            addNewStreetObject(intent.newStreetObject)
        }
    }

    private suspend fun addNewStreetObject(
        newStreetObject: NewStreetObject
    ) {
        dispatch(AddThingStoreFactory.Message.SetLoading)
        when(val response = useCase.addNewThing(newStreetObject)) {
            is ApiResponse.Success -> {
                dispatch(AddThingStoreFactory.Message.SuccessfullyAdded)
            }
            is ApiResponse.Error.HttpError -> {
                dispatch(AddThingStoreFactory.Message.SetHttpError(response))
            }
            is ApiResponse.Error.NetworkError -> {
                dispatch(AddThingStoreFactory.Message.SetNetworkError)
            }
            is ApiResponse.Error.SerializationError -> {
                dispatch(AddThingStoreFactory.Message.SetSerializationError)
            }
            else -> throw IllegalStateException("No such response type")
        }
    }
}