package com.acerolla.ui_android.mappers

import com.acerolla.add_thing_api.ThingsStore
import com.acerolla.common.mappers.BaseMapper
import com.acerolla.ui_android.ThingsUiState

class ThingsUiStateMapper: BaseMapper<ThingsStore.State, ThingsUiState> {

    override fun map(from: ThingsStore.State): ThingsUiState {
        return ThingsUiState(
            from.gotStreetObjects,
            from.isLoading,
            from.error
        )
    }
}