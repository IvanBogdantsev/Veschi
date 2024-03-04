package com.acerolla.add_thing_api

import com.acerolla.add_thing_api.models.CoordinatePoint
import kotlinx.coroutines.flow.Flow

interface ThingsStatePublisher {

    fun publishState(): Flow<ThingsStore.State>

    fun getStreetObjectsForCoordinates(northwest: CoordinatePoint, southwest: CoordinatePoint)

    fun getAllStreetObjects()
}