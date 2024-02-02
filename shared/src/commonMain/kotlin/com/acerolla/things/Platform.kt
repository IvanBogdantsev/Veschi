package com.acerolla.things

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform