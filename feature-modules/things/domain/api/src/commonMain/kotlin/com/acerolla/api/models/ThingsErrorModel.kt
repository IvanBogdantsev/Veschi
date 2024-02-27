package com.acerolla.api.models

data class ThingsErrorModel<T>(
    val code: Int? = null,
    val body: T? = null,
    val msg: String? = null
)
