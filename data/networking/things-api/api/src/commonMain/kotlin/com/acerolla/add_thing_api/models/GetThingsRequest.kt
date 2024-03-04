package com.acerolla.add_thing_api.models

import kotlinx.serialization.Serializable

@Serializable
data class GetThingsRequest(
    val northeast: CoordinatePointDto,
    val southwest: CoordinatePointDto,
)


@Serializable
data class CoordinatePointDto(
    val latitude: Double,
    val longitude: Double,
)
