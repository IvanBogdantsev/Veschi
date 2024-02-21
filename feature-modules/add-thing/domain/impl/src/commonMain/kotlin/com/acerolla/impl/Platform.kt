package com.acerolla.impl

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform