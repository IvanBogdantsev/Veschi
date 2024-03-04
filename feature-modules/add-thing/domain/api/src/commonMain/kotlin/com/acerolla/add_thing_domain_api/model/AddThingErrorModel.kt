package com.acerolla.add_thing_domain_api.model

data class AddThingErrorModel<T>(
    val code: Int? = null,
    val body: T? = null,
    val msg: String? = null
)
