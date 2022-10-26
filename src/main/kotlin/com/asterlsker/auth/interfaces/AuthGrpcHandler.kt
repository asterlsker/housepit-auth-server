package com.asterlsker.auth.interfaces

import refreshRequest


class AuthGrpcHandler {

    fun test() {
        val test = refreshRequest {
            refreshToken = "1"
        }
    }
}