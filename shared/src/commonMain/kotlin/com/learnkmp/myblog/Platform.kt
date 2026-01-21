package com.learnkmp.myblog

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform