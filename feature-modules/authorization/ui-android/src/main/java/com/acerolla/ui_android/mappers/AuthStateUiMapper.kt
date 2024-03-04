package com.acerolla.ui_android.mappers

import com.acerolla.add_thing_api.AuthStore
import com.acerolla.common.mappers.BaseMapper
import com.acerolla.ui_android.UiState

class AuthStateUiMapper: BaseMapper<AuthStore.State, UiState> {

    override fun map(from: AuthStore.State): UiState {
        return UiState(
            from.isSuccessfullySigned,
            from.isLoading,
            from.error
        )
    }
}