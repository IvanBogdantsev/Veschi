package com.acerolla.impl

import com.acerolla.add_thing_domain_api.AddThingStore
import com.acerolla.add_thing_domain_api.model.AddThingErrorModel
import com.arkivanov.mvikotlin.core.store.Reducer

class AddThingsReducer: Reducer<AddThingStore.State, AddThingStoreFactory.Message> {

    override fun AddThingStore.State.reduce(msg: AddThingStoreFactory.Message) = when (msg) {
        is AddThingStoreFactory.Message.SuccessfullyAdded -> copy(
            loading = false,
            success = true,
            failed = null
        )
        is AddThingStoreFactory.Message.SetLoading -> copy(
            loading = true,
            success = false,
            failed = null
        )
        is AddThingStoreFactory.Message.SetHttpError -> copy(
            loading = false,
            success = false,
            failed = AddThingErrorModel(msg.error.code, msg.error.errorBody, "Http exception")
        )
        is AddThingStoreFactory.Message.SetNetworkError -> copy(
            loading = false,
            success = false,
            failed = AddThingErrorModel(msg = "Network error")
        )
        is AddThingStoreFactory.Message.SetSerializationError -> copy(
            loading = false,
            success = false,
            failed = AddThingErrorModel(msg = "Serialization error")
        )
    }
}