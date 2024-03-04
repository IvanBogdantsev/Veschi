package com.acerolla.impl

import com.acerolla.add_thing_api.ThingsStore
import com.acerolla.add_thing_api.models.ThingsErrorModel
import com.arkivanov.mvikotlin.core.store.Reducer

internal class ThingsReducer : Reducer<ThingsStore.State, ThingsStoreFactory.Message> {

    override fun ThingsStore.State.reduce(
        msg: ThingsStoreFactory.Message,
    ) = when (msg) {
        is ThingsStoreFactory.Message.GotStreetObjects -> copy(
            gotStreetObjects = msg.obj,
            isLoading = false,
            error = null
        )
        is ThingsStoreFactory.Message.SetLoading -> copy(
            gotStreetObjects = null,
            isLoading = true,
            error = null
        )
        is ThingsStoreFactory.Message.SetHttpError -> copy(
            gotStreetObjects = null,
            isLoading = false,
            error = ThingsErrorModel(msg.error.code, msg.error.errorBody, "Http exception")
        )
        is ThingsStoreFactory.Message.SetNetworkError -> copy(
            gotStreetObjects = null,
            isLoading = false,
            error = ThingsErrorModel(msg = "Network exception")
        )
        is ThingsStoreFactory.Message.SetSerializationError -> copy(
            gotStreetObjects = null,
            isLoading = false,
            error = ThingsErrorModel(msg = "Serialization exception")
        )
    }
}