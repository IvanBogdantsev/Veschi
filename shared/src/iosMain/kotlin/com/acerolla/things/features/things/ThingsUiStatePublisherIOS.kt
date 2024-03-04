package com.acerolla.things.features.things

import com.acerolla.add_thing_api.ThingsStatePublisher
import com.acerolla.add_thing_api.ThingsStore
import com.acerolla.common.mappers.BaseMapper
import com.acerolla.things.features.BaseUiStatePublisheriOS
import com.acerolla.things.features.things.models.CoordinatePointIOS
import com.acerolla.things.features.things.models.toDomainModel
import com.acerolla.things.utils.FlowWrapper
import kotlinx.coroutines.flow.map

class ThingsUiStatePublisherIOS(
    private val publisher: ThingsStatePublisher,
    private val uiStateMapper: BaseMapper<ThingsStore.State, ThingsUiStateiOS>
): BaseUiStatePublisheriOS() {

    fun publishState(): FlowWrapper<ThingsUiStateiOS> {
        return FlowWrapper(scope, publisher.publishState().map(::mapUiStateToiOSState))
    }

    fun getThingsForCoordinates(northwest: CoordinatePointIOS, southeast: CoordinatePointIOS) {
        publisher.getStreetObjectsForCoordinates(northwest.toDomainModel(), southeast.toDomainModel())
    }

    fun getMockObjects() {
        publisher.getAllStreetObjects()
    }

    private fun mapUiStateToiOSState(state: ThingsStore.State): ThingsUiStateiOS = uiStateMapper.map(state)
}