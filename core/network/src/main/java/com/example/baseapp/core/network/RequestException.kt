package com.example.baseapp.core.network

class RequestException(
    override var message: String = "",
) : Exception(message)
