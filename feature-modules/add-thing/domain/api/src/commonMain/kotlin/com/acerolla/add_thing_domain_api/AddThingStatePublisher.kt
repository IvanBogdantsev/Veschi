package com.acerolla.add_thing_domain_api

import com.acerolla.add_thing_domain_api.model.NewStreetObject
import kotlinx.coroutines.flow.Flow

interface AddThingStatePublisher {

    fun publishState(): Flow<AddThingStore.State>

    fun addNewStreetObject(newStreetObject: NewStreetObject)
}