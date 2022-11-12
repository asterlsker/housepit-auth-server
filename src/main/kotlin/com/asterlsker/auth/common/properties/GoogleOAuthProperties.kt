package com.asterlsker.auth.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.google")
data class GoogleOAuthProperties(
    val clientId: String,
    val clientSecret: String,
    val scope: List<String>
)