package net.ivanvega.localtimeapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform