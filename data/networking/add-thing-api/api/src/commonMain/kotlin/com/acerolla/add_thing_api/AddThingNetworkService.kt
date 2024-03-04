package com.acerolla.add_thing_api

import com.acerolla.add_thing_api.models.NewStreetObjectDto
import com.acerolla.add_thing_api.models.StreetObjectDto
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse

interface AddThingNetworkService {

    suspend fun addNewObject(streetObject: NewStreetObjectDto): ApiResponse<StreetObjectDto, ErrorResponse>
}