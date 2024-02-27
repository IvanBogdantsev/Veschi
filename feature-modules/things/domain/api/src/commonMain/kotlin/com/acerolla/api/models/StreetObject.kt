package com.acerolla.api.models

data class StreetObjectsForCoordinate(
    val objects: List<StreetObject> = emptyList()
)

data class StreetObject(
    val objectId: String,
    val userId: String,
    val claimedUsersId: List<String>,
    val likedUsersId: List<String>,
    val name: String?,
    val description: String?,
    val vicinity: String,
    val geometry: Geometry,
    val ownedByUser: Boolean,
    val category: Category?,
    val keywords: List<String>,
    val images: List<String>,
    val thumbnailImage: String,
    val conditionIcon: String?,
    val timestamp: Int,
    val condition: Condition?
)

data class NewStreetObject(
    val userId: String,
    val name: String?,
    val description: String?,
    val vicinity: String,
    val geometry: Geometry,
    val ownedByUser: Boolean,
    val category: Category?,
    val images: List<String>,
    val thumbnailImage: String,
    val conditionIcon: String?,
    val timestamp: Int,
    val condition: Condition?
)

data class Geometry(
    val latitude: Double,
    val longitude: Double,
    val accuracy: Double,
)

enum class Category {

    BOOKS, FURNITURE, ELECTRONICS, OTHER
}

enum class Condition {

    BRAND_NEW, LIKE_NEW, EXCELLENT, VERY_GOOD, GOOD, FAIR, POOR
}