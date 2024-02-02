package com.acerolla.data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform