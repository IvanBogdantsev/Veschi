package com.acerolla.common.mappers

import com.acerolla.common.ApiResponse

interface BaseApiResponseMapper<T, V> {

    fun <E> map(from: ApiResponse<T, E>): ApiResponse<V, E>
}