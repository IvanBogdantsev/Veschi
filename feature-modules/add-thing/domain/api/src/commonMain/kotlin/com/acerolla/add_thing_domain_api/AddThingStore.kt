package com.acerolla.add_thing_domain_api

import com.acerolla.add_thing_domain_api.model.AddThingErrorModel
import com.acerolla.add_thing_domain_api.model.NewStreetObject
import com.acerolla.common.ErrorResponse
import com.arkivanov.mvikotlin.core.store.Store

interface AddThingStore: Store<AddThingStore.Intent, AddThingStore.State, AddThingStore.Label> {

    data class State(
        val loading: Boolean = false,
        val success: Boolean = false,
        val failed: AddThingErrorModel<ErrorResponse>? = null
    )

    data class Label(
        val toastString: String
    )

    sealed interface Intent {

        data class AddNewStreetObject(
            val newStreetObject: NewStreetObject
        ): Intent
    }
}