package com.acerolla.profile_domain_api

import kotlinx.coroutines.flow.Flow

interface ProfileStatePublisher {

    fun publishState(): Flow<ProfileStore.State>

    fun logout()

    fun deleteAccount()
}