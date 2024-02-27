package com.acerolla.api.models

import kotlinx.serialization.Serializable

@Serializable
data class StreetObjectResponse(
    val status: String,
    val streetObjects: List<StreetObjectDto>
)

@Serializable
data class StreetObjectDto(
    val objectId: String,
    val userId: String,
    val claimedUsersId: List<String>,
    val likedUsersId: List<String>,
    val name: String?,
    val description: String?,
    val vicinity: String,
    val geometry: GeometryDto,
    val ownedByUser: Boolean,
    val category: String?,
    val keywords: List<String>,
    val images: List<String>,
    val thumbnailImage: String,
    val conditionIcon: String?,
    val timestamp: Int,
    val condition: String?
)

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

@Serializable
data class GeometryDto(
    val latitude: Double,
    val longitude: Double,
    val accuracy: Double,
)