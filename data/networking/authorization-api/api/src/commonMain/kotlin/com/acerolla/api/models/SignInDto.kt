package com.acerolla.api.models

import kotlinx.serialization.Serializable

@Serializable
data class SignInDto(
    val email: String,
    val password: String
)
