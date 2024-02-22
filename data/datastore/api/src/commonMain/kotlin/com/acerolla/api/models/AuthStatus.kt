package com.acerolla.api.models

import kotlin.jvm.JvmInline

@JvmInline
value class AuthStatus(
    val authorized: Boolean?
)