package com.acerolla.api.models

import kotlin.jvm.JvmInline

@JvmInline
value class RefreshToken(
    val token: String?
)