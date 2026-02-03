package net.ivanvega.miprimercomposemultiplatfom

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}