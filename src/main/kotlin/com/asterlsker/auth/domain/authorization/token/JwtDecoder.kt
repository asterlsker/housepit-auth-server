package com.asterlsker.auth.domain.authorization.token

import org.springframework.stereotype.Component

@Component
class JwtDecoder {

    fun decodeBase64(token: String): String {
        // TODO 추후에는 email 이 아닌 decode obj 일 수 있음
        return "designjava@gmail.com"
    }
}