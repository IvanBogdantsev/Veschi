package com.acerolla.implementation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform