package com.asterlsker.auth.domain.authorization.token

import com.asterlsker.auth.domain.model.Provider
import org.springframework.stereotype.Component

@Component
class JwtDecoder {

    // TODO provider 별로 디코드 분기
    fun decodeBase64(token: String, provider: Provider): String {
        return "designjava@gmail.com"
    }
}