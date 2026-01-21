package com.learnkmp.myblog

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello,  ballsy balls${platform.name}!"
    }
}