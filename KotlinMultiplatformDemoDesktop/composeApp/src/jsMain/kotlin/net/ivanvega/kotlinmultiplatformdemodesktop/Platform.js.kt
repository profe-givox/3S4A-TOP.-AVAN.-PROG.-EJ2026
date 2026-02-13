package net.ivanvega.kotlinmultiplatformdemodesktop

class JsPlatform : Platform {
    override val name: String = "Web with Kotlin/JS"
}

actual fun getPlatform(): Platform = JsPlatform()