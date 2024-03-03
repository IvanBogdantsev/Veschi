package com.acerolla.data

import com.acerolla.add_thing_domain_api.AddThingsUseCase
import com.acerolla.api.AddThingNetworkService

class AddThingsUseCaseImpl(
    private val addThingsNetworkService: AddThingNetworkService
): AddThingsUseCase {

    override suspend fun addNewThing() {
        TODO("Not yet implemented")
    }
}