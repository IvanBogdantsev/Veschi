package com.acerolla.add_thing_domain_api.model

data class NewStreetObject(
    val userId: String = "",
    val name: String? = null,
    val description: String? = null,
    val vicinity: String = "",
    val geometry: Geometry = Geometry(0.0, 0.0, 0.0),
    val ownedByUser: Boolean = false,
    val category: Category? = null,
    val images: List<String> = emptyList(),
    val thumbnailImage: String = "",
    val conditionIcon: String? = null,
    val timestamp: Int = -1,
    val condition: Condition? = null
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