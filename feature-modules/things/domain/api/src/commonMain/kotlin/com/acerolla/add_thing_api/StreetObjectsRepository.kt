package com.acerolla.add_thing_api

import com.acerolla.add_thing_api.models.CoordinatePoint
import com.acerolla.add_thing_api.models.StreetObject
import com.acerolla.add_thing_api.models.StreetObjectUUID
import com.acerolla.add_thing_api.models.StreetObjectsForCoordinate
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse

interface StreetObjectsRepository {

    suspend fun getStreetObjectsForCoordinate(northwest: CoordinatePoint, southeast: CoordinatePoint): ApiResponse<StreetObjectsForCoordinate, ErrorResponse>

    suspend fun getStreetObjectInfoByUUID(uuid: StreetObjectUUID): ApiResponse<StreetObject, ErrorResponse>

    suspend fun deleteStreetObjectByUUID(uuid: StreetObjectUUID)

    // TODO: for tests
    suspend fun getAllStreetObjects(): ApiResponse<StreetObjectsForCoordinate, ErrorResponse>
}