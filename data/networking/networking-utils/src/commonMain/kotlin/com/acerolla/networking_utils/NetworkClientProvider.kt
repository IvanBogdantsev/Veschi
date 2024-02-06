package com.acerolla.networking_utils

import io.ktor.client.HttpClient

interface NetworkClientProvider {

    fun provideBaseHttpClient(): HttpClient

    fun provideJwtHttpClient(): HttpClient
}