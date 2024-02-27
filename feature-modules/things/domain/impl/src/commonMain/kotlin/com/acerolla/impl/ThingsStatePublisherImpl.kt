package com.acerolla.impl

import com.acerolla.api.ThingsStatePublisher
import com.acerolla.api.ThingsStore
import com.acerolla.api.models.CoordinatePoint
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
}