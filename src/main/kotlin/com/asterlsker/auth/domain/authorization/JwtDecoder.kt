package com.asterlsker.auth.domain.authorization

import org.springframework.stereotype.Component

@Component
class JwtDecoder {

    fun decodeBase64(token: String): String {
        // TODO implementation
        return "decode$token"
    }
}