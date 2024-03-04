package com.acerolla.things.features.things.models

import com.acerolla.add_thing_api.models.CoordinatePoint

data class CoordinatePointIOS(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)

fun CoordinatePointIOS.toDomainModel(): CoordinatePoint {
    return CoordinatePoint(latitude, longitude)
}