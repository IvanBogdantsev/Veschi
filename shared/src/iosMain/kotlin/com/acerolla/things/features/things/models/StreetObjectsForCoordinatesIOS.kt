package com.acerolla.things.features.things.models

import com.acerolla.add_thing_api.models.Geometry
import com.acerolla.add_thing_api.models.StreetObject

data class StreetObjectsForCoordinatesIOS(
    val objects: List<StreetObjectIOS>
)

data class StreetObjectIOS(
    val objectId: String,
    val userId: String,
    val claimedUsersId: List<String>,
    val likedUsersId: List<String>,
    val name: String?,
    val description: String?,
    val vicinity: String,
    val geometry: GeometryIOS,
    val ownedByUser: Boolean,
    val category: String?,
    val keywords: List<String>,
    val images: List<String>,
    val thumbnailImage: String,
    val conditionIcon: String?,
    val timestamp: Int,
    val condition: String?
)

data class GeometryIOS(
    val latitude: Double,
    val longitude: Double,
    val accuracy: Double,
)

fun StreetObject.toIOS(): StreetObjectIOS {
    return StreetObjectIOS(
        objectId = objectId,
        userId = userId,
        claimedUsersId = claimedUsersId,
        likedUsersId = likedUsersId,
        name = name,
        description = description,
        vicinity = vicinity,
        geometry = geometry.toIOS(),
        ownedByUser = ownedByUser,
        category = category?.name,
        keywords = keywords,
        images = images,
        thumbnailImage = thumbnailImage,
        conditionIcon = conditionIcon,
        timestamp = timestamp,
        condition = condition?.name
    )
}

fun Geometry.toIOS(): GeometryIOS {
    return GeometryIOS(
        latitude,
        longitude,
        accuracy
    )
}