package net.ivanvega.miprimercomposemultiplatfom

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform