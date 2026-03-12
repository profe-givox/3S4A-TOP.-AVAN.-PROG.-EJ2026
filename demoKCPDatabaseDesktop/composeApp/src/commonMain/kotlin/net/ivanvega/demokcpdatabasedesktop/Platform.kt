package net.ivanvega.demokcpdatabasedesktop

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform