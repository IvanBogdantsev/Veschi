package com.acerolla.things.features.things

import com.acerolla.add_thing_api.ThingsStore
import com.acerolla.common.mappers.BaseMapper
import com.acerolla.things.features.things.models.StreetObjectsForCoordinatesIOS
import com.acerolla.things.features.things.models.toIOS
import com.acerolla.things.utils.ErrorModeliOS

class ThingsUiStateToiOSMapper: BaseMapper<ThingsStore.State, ThingsUiStateiOS> {

    override fun map(from: ThingsStore.State): ThingsUiStateiOS {
        return ThingsUiStateiOS(
            StreetObjectsForCoordinatesIOS(
                from.gotStreetObjects?.objects?.map { it.toIOS() } ?: emptyList()
            ),
            from.isLoading,
            ErrorModeliOS(
                from.error?.code,
                from.error?.msg
            )
        )
    }
}