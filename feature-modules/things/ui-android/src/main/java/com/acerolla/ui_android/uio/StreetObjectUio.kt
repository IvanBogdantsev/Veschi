package com.acerolla.ui_android.uio

import androidx.compose.runtime.Immutable
import com.acerolla.api.models.Category
import com.acerolla.api.models.Condition

@Immutable
data class StreetObjectUio(
    val objectId: String,
    val userId: String,
    val claimedUsersId: List<String>,
    val likedUsersId: List<String>,
    val name: String?,
    val description: String?,
    val vicinity: String,
    val geometry: GeometryUio,
    val ownedByUser: Boolean,
    val category: Category?,
    val keywords: List<String>,
    val images: List<String>,
    val thumbnailImage: String,
    val conditionIcon: String?,
    val timestamp: Int,
    val condition: Condition?
)

@Immutable
data class GeometryUio(
    val latitude: Double,
    val longitude: Double,
    val accuracy: Double,
)
