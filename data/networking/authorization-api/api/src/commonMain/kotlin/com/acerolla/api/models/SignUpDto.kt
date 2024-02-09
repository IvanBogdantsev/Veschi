package com.acerolla.api.models

import kotlinx.serialization.Serializable

@Serializable
data class SignUpDto(
    val email: String,
    val username: String,
    val password: String
)
