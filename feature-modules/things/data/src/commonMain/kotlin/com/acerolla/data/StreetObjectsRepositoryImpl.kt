package com.acerolla.data

import com.acerolla.add_thing_api.StreetObjectsRepository
import com.acerolla.add_thing_api.ThingsNetworkService
import com.acerolla.add_thing_api.models.CoordinatePoint
import com.acerolla.add_thing_api.models.StreetObject
import com.acerolla.add_thing_api.models.StreetObjectDto
import com.acerolla.add_thing_api.models.StreetObjectResponse
import com.acerolla.add_thing_api.models.StreetObjectUUID
import com.acerolla.add_thing_api.models.StreetObjectsForCoordinate
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse
import com.acerolla.common.mappers.BaseApiResponseMapper
import com.acerolla.data.mappers.toDomainModel
import com.acerolla.data.mappers.toDto

class StreetObjectsRepositoryImpl(
    private val thingsNetworkService: ThingsNetworkService,
    private val streetObjectResponseDtoToDomainModelMapper: BaseApiResponseMapper<StreetObjectResponse, StreetObjectsForCoordinate>,
    private val streetObjectDtoToDomainMapper: BaseApiResponseMapper<StreetObjectDto, StreetObject>
): StreetObjectsRepository {

    override suspend fun getStreetObjectsForCoordinate(
        northwest: CoordinatePoint,
        southeast: CoordinatePoint
    ): ApiResponse<StreetObjectsForCoordinate, ErrorResponse> {
        // FIXME: Move logic to mapper
        return when(val from = thingsNetworkService.getStreetObjectsForCoordinate(northwest.toDto(), southeast.toDto())) {
            is ApiResponse.Success -> ApiResponse.Success(from.body.toDomainModel())
            is ApiResponse.Error.HttpError -> ApiResponse.Error.HttpError(from.code, from.errorBody)
            is ApiResponse.Error.NetworkError -> ApiResponse.Error.NetworkError
            is ApiResponse.Error.SerializationError -> ApiResponse.Error.SerializationError
            is ApiResponse.Error.TimeoutError -> ApiResponse.Error.TimeoutError
        }
    }

    override suspend fun getStreetObjectInfoByUUID(uuid: StreetObjectUUID): ApiResponse<StreetObject, ErrorResponse> {
        return streetObjectDtoToDomainMapper.map(
            thingsNetworkService.getStreetObjectInfoByUUID(uuid.toDto())
        )
    }

    override suspend fun deleteStreetObjectByUUID(uuid: StreetObjectUUID) {
        thingsNetworkService.deleteStreetObjectByUUID(uuid.toDto())
    }

    override suspend fun getAllStreetObjects(): ApiResponse<StreetObjectsForCoordinate, ErrorResponse> {
        // FIXME: Move logic to mapper
        return when(val from = thingsNetworkService.getAllStreetObjects()) {
            is ApiResponse.Success -> ApiResponse.Success(from.body.toDomainModel())
            is ApiResponse.Error.HttpError -> ApiResponse.Error.HttpError(from.code, from.errorBody)
            is ApiResponse.Error.NetworkError -> ApiResponse.Error.NetworkError
            is ApiResponse.Error.SerializationError -> ApiResponse.Error.SerializationError
            is ApiResponse.Error.TimeoutError -> ApiResponse.Error.TimeoutError
        }
    }
}