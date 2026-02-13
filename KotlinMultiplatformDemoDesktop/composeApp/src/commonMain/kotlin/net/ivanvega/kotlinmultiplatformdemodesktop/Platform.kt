package net.ivanvega.kotlinmultiplatformdemodesktop

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform