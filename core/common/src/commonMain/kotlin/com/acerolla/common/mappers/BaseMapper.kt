package com.acerolla.common.mappers

interface BaseMapper<T, V> {

    fun map(from: T): V
}