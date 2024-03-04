package com.acerolla.things.features.profile

import com.acerolla.common.mappers.BaseMapper
import com.acerolla.profile_domain_api.ProfileStore

class ProfileUiStateToiOSMapper: BaseMapper<ProfileStore.State, ProfileUiStateiOS> {

    override fun map(from: ProfileStore.State): ProfileUiStateiOS {
        return ProfileUiStateiOS(
            from.isLoading
        )
    }
}