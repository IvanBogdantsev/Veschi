package com.acerolla.things.features.authorization

import com.acerolla.add_thing_api.AuthStatePublisher
import com.acerolla.add_thing_api.AuthStore
import com.acerolla.common.mappers.BaseMapper
import com.acerolla.things.features.BaseUiStatePublisheriOS
import com.acerolla.things.utils.FlowWrapper
import kotlinx.coroutines.flow.map

class AuthStatePublisheriOS(
    private val publisher: AuthStatePublisher,
    private val uiStateMapper: BaseMapper<AuthStore.State, AuthUiStateiOS>
): BaseUiStatePublisheriOS() {

    fun publishState(): FlowWrapper<AuthUiStateiOS> {
        return FlowWrapper(scope, publisher.publishState().map(::mapUiStateToiOSState))
    }

    private fun mapUiStateToiOSState(state: AuthStore.State): AuthUiStateiOS = uiStateMapper.map(state)
}