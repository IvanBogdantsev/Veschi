package com.acerolla.ui_android.mappers

import com.acerolla.api.models.Geometry
import com.acerolla.api.models.StreetObject
import com.acerolla.ui_android.uio.GeometryUio
import com.acerolla.ui_android.uio.StreetObjectUio

fun StreetObject.toUio(): StreetObjectUio {
    return StreetObjectUio(
        objectId = objectId,
        userId = userId,
        claimedUsersId = claimedUsersId,
        likedUsersId = likedUsersId,
        name = name,
        description = description,
        vicinity = vicinity,
        geometry = geometry.toUio(),
        ownedByUser = ownedByUser,
        category = category,
        keywords = keywords,
        images = images,
        thumbnailImage = thumbnailImage,
        conditionIcon = conditionIcon,
        timestamp = timestamp,
        condition = condition
    )
}

fun Geometry.toUio(): GeometryUio {
    return GeometryUio(latitude, longitude, accuracy)
}