package com.acerolla.impl

import com.acerolla.add_thing_api.ThingsStatePublisher
import com.acerolla.add_thing_api.ThingsStore
import com.acerolla.add_thing_api.models.CoordinatePoint
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.flow.Flow

class ThingsStatePublisherImpl(
    private val store: ThingsStore,
): ThingsStatePublisher {

    override fun publishState(): Flow<ThingsStore.State> = store.states

    override fun getStreetObjectsForCoordinates(
        northwest: CoordinatePoint,
        southwest: CoordinatePoint
    ) {
        store.accept(ThingsStore.Intent.GetStreetObjectsForCoordinates(northwest, southwest))
    }

    override fun getAllStreetObjects() {
        store.accept(ThingsStore.Intent.GetAllStreetObjects)
    }
}