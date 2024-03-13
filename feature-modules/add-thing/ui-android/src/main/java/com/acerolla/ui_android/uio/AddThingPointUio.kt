package com.acerolla.ui_android.uio

import androidx.compose.runtime.Immutable

@Immutable
data class AddThingPointUio(
    val latitude: Double,
    val longitude: Double,
    val accuracy: Double,
)
