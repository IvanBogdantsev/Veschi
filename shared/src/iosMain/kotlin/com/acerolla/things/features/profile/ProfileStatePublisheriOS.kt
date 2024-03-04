package com.acerolla.things.features.profile

import com.acerolla.common.mappers.BaseMapper
import com.acerolla.profile_domain_api.ProfileStatePublisher
import com.acerolla.profile_domain_api.ProfileStore
import com.acerolla.things.features.BaseUiStatePublisheriOS
import com.acerolla.things.utils.FlowWrapper
import kotlinx.coroutines.flow.map

class ProfileStatePublisheriOS(
    private val publisher: ProfileStatePublisher,
    private val uiStateMapper: BaseMapper<ProfileStore.State, ProfileUiStateiOS>
): BaseUiStatePublisheriOS() {

    fun publishState(): FlowWrapper<ProfileUiStateiOS> {
        return FlowWrapper(scope, publisher.publishState().map(::mapUiStateToiOSState))
    }

    private fun mapUiStateToiOSState(state: ProfileStore.State): ProfileUiStateiOS = uiStateMapper.map(state)
}