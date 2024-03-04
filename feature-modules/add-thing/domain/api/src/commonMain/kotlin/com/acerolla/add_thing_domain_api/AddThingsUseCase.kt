package com.acerolla.add_thing_domain_api

import com.acerolla.add_thing_domain_api.model.AddStreetObjectResult
import com.acerolla.add_thing_domain_api.model.NewStreetObject
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse

interface AddThingsUseCase {

    suspend fun addNewThing(newStreetObject: NewStreetObject): ApiResponse<AddStreetObjectResult, ErrorResponse>
}