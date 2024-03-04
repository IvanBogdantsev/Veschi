package com.acerolla.impl

import com.acerolla.add_thing_domain_api.AddThingStatePublisher
import com.acerolla.add_thing_domain_api.AddThingStore
import com.acerolla.add_thing_domain_api.model.NewStreetObject
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.flow.Flow

class AddThingStatePublisherImpl(
    private val addThingStore: AddThingStore
): AddThingStatePublisher {

    override fun publishState(): Flow<AddThingStore.State> = addThingStore.states

    override fun addNewStreetObject(newStreetObject: NewStreetObject) {
        addThingStore.accept(AddThingStore.Intent.AddNewStreetObject(newStreetObject))
    }
}