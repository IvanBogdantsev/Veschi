package com.acerolla.impl

import com.acerolla.common.BaseExecutor
import com.acerolla.profile_domain_api.ProfileRepository
import com.acerolla.profile_domain_api.ProfileStore

internal class ProfileExecutor(
    private val repository: ProfileRepository,
) : BaseExecutor<ProfileStore.Intent, Nothing, ProfileStore.State, ProfileStoreFactory.Message, Nothing>() {

    override suspend fun suspendExecuteIntent(
        intent: ProfileStore.Intent,
        getState: () -> ProfileStore.State,
    ) = when (intent) {
        ProfileStore.Intent.Logout -> {}
    }
}
