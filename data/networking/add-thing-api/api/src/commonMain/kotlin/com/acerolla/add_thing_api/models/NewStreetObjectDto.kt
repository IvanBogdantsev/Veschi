package com.acerolla.add_thing_api.models

import kotlinx.serialization.Serializable

@Serializable
data class NewStreetObjectDto(
    val userId: String,
    val name: String?,
    val description: String?,
    val vicinity: String,
    val geometry: GeometryDto,
    val ownedByUser: Boolean,
    val category: String?,
    val images: List<String>,
    val thumbnailImage: String,
    val conditionIcon: String?,
    val timestamp: Int,
    val condition: String?
)
