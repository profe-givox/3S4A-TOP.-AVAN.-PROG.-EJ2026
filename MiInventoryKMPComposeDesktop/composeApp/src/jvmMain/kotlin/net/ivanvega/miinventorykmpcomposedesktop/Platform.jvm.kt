package net.ivanvega.miinventorykmpcomposedesktop

class JVMPlatform : Platform {
    //override val name: String = "Java ${System.getProperty("java.version")}"
    override val name: String = "Desktop"
}

actual fun getPlatform(): Platform = JVMPlatform()