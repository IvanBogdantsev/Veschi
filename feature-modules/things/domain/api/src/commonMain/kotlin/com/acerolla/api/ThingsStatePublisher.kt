package com.acerolla.api

import com.acerolla.api.models.CoordinatePoint
import kotlinx.coroutines.flow.Flow

interface ThingsStatePublisher {

    fun publishState(): Flow<ThingsStore.State>

    fun getStreetObjectsForCoordinates(northwest: CoordinatePoint, southwest: CoordinatePoint)

    fun getAllStreetObjects()
}