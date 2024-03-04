package com.acerolla.add_thing_api

import com.acerolla.add_thing_api.models.Secret
import com.acerolla.add_thing_api.models.SignInDto
import com.acerolla.add_thing_api.models.SignUpDto
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse
import com.acerolla.common.TokenResponse

interface AuthorizationNetworkService {

    suspend fun signIn(dto: SignInDto): ApiResponse<TokenResponse, ErrorResponse>

    suspend fun signUp(dto: SignUpDto): ApiResponse<TokenResponse, ErrorResponse>

    suspend fun getSecret(): ApiResponse<Secret, ErrorResponse>
}