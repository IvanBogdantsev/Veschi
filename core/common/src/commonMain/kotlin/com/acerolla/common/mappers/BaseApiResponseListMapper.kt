package com.acerolla.common.mappers

import com.acerolla.common.ApiResponse

interface BaseApiResponseListMapper<T, V> {

    fun <E> map(from: ApiResponse<List<T>, E>): ApiResponse<List<V>, E>
}