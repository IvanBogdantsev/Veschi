package com.acerolla.things.features.addThing.models

import com.acerolla.add_thing_domain_api.model.Category
import com.acerolla.add_thing_domain_api.model.Condition
import com.acerolla.add_thing_domain_api.model.Geometry
import com.acerolla.add_thing_domain_api.model.NewStreetObject
import com.acerolla.things.features.things.models.GeometryIOS

data class NewStreetObjectIOS(
    val userId: String,
    val name: String?,
    val description: String?,
    val vicinity: String,
    val geometry: GeometryIOS,
    val ownedByUser: Boolean,
    val category: Category?,
    val images: List<String>,
    val thumbnailImage: String,
    val conditionIcon: String?,
    val timestamp: Int,
    val condition: Condition?
)

fun NewStreetObjectIOS.toDomainModel(): NewStreetObject {
    return NewStreetObject(
        userId,
        name,
        description,
        vicinity,
        geometry.toDomainModel(),
        ownedByUser,
        Category.BOOKS, // test
        images,
        thumbnailImage,
        conditionIcon,
        timestamp,
        Condition.FAIR // test
    )
}

fun GeometryIOS.toDomainModel(): Geometry {
    return Geometry(latitude, longitude, accuracy)
}