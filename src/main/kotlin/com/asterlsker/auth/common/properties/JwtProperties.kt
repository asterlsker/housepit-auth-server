package com.asterlsker.auth.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "security.jwt")
class JwtProperties(
    val token: TokenProperties
) {
    @ConstructorBinding
    data class TokenProperties(
        val secretKey: String,
        val accessTokenExpireTime: Long,
        val refreshTokenExpireTime: Long
    )
}