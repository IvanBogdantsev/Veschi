package com.acerolla.data.mappers

import com.acerolla.add_thing_domain_api.model.Geometry
import com.acerolla.add_thing_domain_api.model.NewStreetObject
import com.acerolla.add_thing_api.models.GeometryDto
import com.acerolla.add_thing_api.models.NewStreetObjectDto

fun NewStreetObject.toDto(): NewStreetObjectDto {
    return NewStreetObjectDto(
        userId,
        name,
        description,
        vicinity,
        geometry.toDto(),
        ownedByUser,
        category?.name,
        images,
        thumbnailImage,
        conditionIcon,
        timestamp,
        condition?.name
    )
}

fun Geometry.toDto(): GeometryDto {
    return GeometryDto(
        latitude = latitude,
        longitude = longitude,
        accuracy = accuracy
    )
}