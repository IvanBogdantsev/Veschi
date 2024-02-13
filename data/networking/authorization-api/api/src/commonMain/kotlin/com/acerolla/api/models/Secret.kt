package com.acerolla.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Secret(
    val secret: String
)
