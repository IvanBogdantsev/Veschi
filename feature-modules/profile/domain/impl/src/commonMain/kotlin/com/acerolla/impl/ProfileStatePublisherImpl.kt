package com.acerolla.impl

import com.acerolla.profile_domain_api.ProfileStatePublisher
import com.acerolla.profile_domain_api.ProfileStore
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.flow.Flow

class ProfileStatePublisherImpl(
    private val store: ProfileStore
): ProfileStatePublisher {

    override fun publishState(): Flow<ProfileStore.State> = store.states

    override fun logout() {

    }

    override fun deleteAccount() {

    }
}