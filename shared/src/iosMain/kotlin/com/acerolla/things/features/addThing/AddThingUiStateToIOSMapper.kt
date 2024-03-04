package com.acerolla.things.features.addThing

import com.acerolla.add_thing_domain_api.AddThingStore
import com.acerolla.common.mappers.BaseMapper
import com.acerolla.things.utils.ErrorModeliOS

class AddThingUiStateToIOSMapper: BaseMapper<AddThingStore.State, AddThingUiStateIOS> {

    override fun map(from: AddThingStore.State): AddThingUiStateIOS {
        return AddThingUiStateIOS(
            from.success,
            from.loading,
            ErrorModeliOS(
                from.failed?.code,
                from.failed?.msg
            )
        )
    }
}