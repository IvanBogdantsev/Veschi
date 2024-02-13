package com.acerolla.networking_utils.jwt

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenDto(
    @SerialName("refreshToken") val refreshToken: String?
)
