package com.acerolla.things.features.authorization

import com.acerolla.things.utils.ErrorModeliOS

data class AuthUiStateiOS(
    val successfullySigned: Boolean = false,
    val isLoading: Boolean = false,
    val error: ErrorModeliOS? = null
)
