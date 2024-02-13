package com.acerolla.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Secret(
    @SerialName("secret") val secret: String
)
