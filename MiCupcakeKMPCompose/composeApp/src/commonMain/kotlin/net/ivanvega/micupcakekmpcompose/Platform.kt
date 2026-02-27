package net.ivanvega.micupcakekmpcompose

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform