package com.acerolla.things.features.addThing

import com.acerolla.add_thing_domain_api.AddThingStatePublisher
import com.acerolla.add_thing_domain_api.AddThingStore
import com.acerolla.common.mappers.BaseMapper
import com.acerolla.things.features.BaseUiStatePublisheriOS
import com.acerolla.things.features.addThing.models.NewStreetObjectIOS
import com.acerolla.things.features.addThing.models.toDomainModel
import com.acerolla.things.utils.FlowWrapper
import kotlinx.coroutines.flow.map

class AddThingIOSStatePublisher(
    private val publisher: AddThingStatePublisher,
    private val uiStateMapper: BaseMapper<AddThingStore.State, AddThingUiStateIOS>
): BaseUiStatePublisheriOS() {

    fun publishState(): FlowWrapper<AddThingUiStateIOS> {
        return FlowWrapper(scope, publisher.publishState().map(::mapUiStateToiOSState))
    }

    fun addNewThing(newObject: NewStreetObjectIOS) {
        publisher.addNewStreetObject(newObject.toDomainModel())
    }

    private fun mapUiStateToiOSState(state: AddThingStore.State): AddThingUiStateIOS = uiStateMapper.map(state)
}