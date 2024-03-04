package com.acerolla.things.features.addThing

import com.acerolla.things.utils.ErrorModeliOS

data class AddThingUiStateIOS(
    val successfullyAdded: Boolean = false,
    val isLoading: Boolean = false,
    val error: ErrorModeliOS? = null
)
