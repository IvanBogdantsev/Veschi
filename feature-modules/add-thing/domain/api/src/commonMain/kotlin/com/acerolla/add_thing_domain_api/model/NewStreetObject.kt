package com.acerolla.add_thing_domain_api.model

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