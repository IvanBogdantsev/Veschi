package com.acerolla.ui_android.mappers

import com.acerolla.add_thing_domain_api.AddThingStore
import com.acerolla.ui_android.AddThingUiState

fun AddThingStore.State.toUiState(): AddThingUiState {
    return AddThingUiState(
        this.success,
        this.loading,
        this.failed
    )
}