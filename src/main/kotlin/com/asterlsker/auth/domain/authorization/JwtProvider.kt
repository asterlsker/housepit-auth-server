package com.asterlsker.auth.domain.authorization

import com.asterlsker.auth.common.constant.RFK_CACHE_NAME
import com.asterlsker.auth.common.constant.RFK_KEY
import com.asterlsker.auth.common.properties.JwtProperties
import com.asterlsker.auth.common.support.RedisClient
import com.asterlsker.auth.common.support.toMilliSec
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeUnit

@Component
class JwtProvider(
    private val objectMapper: ObjectMapper,
    private val redisClient: RedisClient,
    private val jwtProperties: JwtProperties
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun issueTokens(request: TokenIssueSpec): TokenResponse {
        val payload = objectMapper.writeValueAsString(request)
        return TokenResponse(
            accessToken = issueAccessToken(payload),
            refreshToken = issueRefreshToken(payload)
        )
    }

    private fun issueAccessToken(payload: String): String {
        val now = Date()
        val validity = Date(now.time + jwtProperties.token.accessTokenExpireTime.toMilliSec())

        return Jwts.builder()
            .setClaims(createClaims(payload))
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, jwtProperties.token.secretKey)
            .compact()
    }

    private fun issueRefreshToken(payload: String): String {
        val now = Date()
        val expireTime = jwtProperties.token.refreshTokenExpireTime.toMilliSec()
        val validity = Date(now.time + expireTime)

        val refreshToken = Jwts.builder()
            .setClaims(createClaims(payload))
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, jwtProperties.token.secretKey)
            .compact()

        redisClient.set(
            cacheName = RFK_CACHE_NAME,
            key = RFK_KEY + payload,
            value = refreshToken,
            expireTime = expireTime,
            timeUnit = TimeUnit.MILLISECONDS
        )

        return refreshToken
    }

    private fun createClaims(payload: String) = Jwts.claims().setSubject(payload)

}