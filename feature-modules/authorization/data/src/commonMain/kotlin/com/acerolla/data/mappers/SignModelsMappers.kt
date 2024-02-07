package com.acerolla.data.mappers

import com.acerolla.api.models.SignInDto
import com.acerolla.api.models.SignInModel
import com.acerolla.api.models.SignUpDto
import com.acerolla.api.models.SignUpModel

fun SignUpModel.toDto() = SignUpDto(email, username, password)

fun SignInModel.toDto() = SignInDto(email, password)