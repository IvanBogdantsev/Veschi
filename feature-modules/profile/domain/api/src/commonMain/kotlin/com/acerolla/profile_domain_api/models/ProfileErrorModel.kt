package com.acerolla.profile_domain_api.models

data class ProfileErrorModel<T>(
    val code: Int? = null,
    val body: T? = null,
    val msg: String? = null
)
