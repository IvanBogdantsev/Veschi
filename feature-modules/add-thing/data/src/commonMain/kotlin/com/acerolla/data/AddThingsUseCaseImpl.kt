package com.acerolla.data

import com.acerolla.add_thing_domain_api.AddThingsUseCase
import com.acerolla.add_thing_domain_api.model.AddStreetObjectResult
import com.acerolla.add_thing_domain_api.model.NewStreetObject
import com.acerolla.add_thing_api.AddThingNetworkService
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse
import com.acerolla.data.mappers.toDto

class AddThingsUseCaseImpl(
    private val addThingsNetworkService: AddThingNetworkService
): AddThingsUseCase {

    override suspend fun addNewThing(newStreetObject: NewStreetObject): ApiResponse<AddStreetObjectResult, ErrorResponse> {
        return when(addThingsNetworkService.addNewObject(newStreetObject.toDto())) {
            is ApiResponse.Success -> ApiResponse.Success(AddStreetObjectResult.Success)
            else -> ApiResponse.Success(AddStreetObjectResult.Failed)
        }
    }
}