package com.asterlsker.auth.config

import com.asterlsker.auth.common.properties.GoogleOAuthProperties
import com.asterlsker.auth.domain.model.Provider
import com.asterlsker.auth.infrastructure.authorization.GoogleOAuthTokenDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OAuthTokenDecoderConfig(
    private val googleOAuthProperties: GoogleOAuthProperties,
) {

    companion object {
        const val BEAN_NAME_SUFFIX = "OAuthTokenDecoder"
    }

    @Bean("google${BEAN_NAME_SUFFIX}")
    fun googleOAuthTokenDecoder(): GoogleOAuthTokenDecoder {
        return GoogleOAuthTokenDecoder(googleOAuthProperties)
    }
}