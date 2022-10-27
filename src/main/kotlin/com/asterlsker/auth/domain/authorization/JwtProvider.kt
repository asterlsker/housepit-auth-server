package com.asterlsker.auth.domain.authorization

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class JwtProvider(
    private val objectMapper: ObjectMapper
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    fun issueTokens(request: TokenIssueSpec): TokenResponse {
        val payload = objectMapper.writeValueAsString(request)
        return TokenResponse(
            accessToken = "ak",
            refreshToken = "rfk"
        )
    }

}