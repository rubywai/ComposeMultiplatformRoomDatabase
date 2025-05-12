package com.rubylearner.roomdb

class Greeting {
    private val platform = "Android"

    fun greet(): String {
        return "Hello, ${platform}!"
    }
}