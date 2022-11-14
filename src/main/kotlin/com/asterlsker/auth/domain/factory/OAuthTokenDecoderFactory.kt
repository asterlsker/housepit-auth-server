package com.asterlsker.auth.domain.factory

import com.asterlsker.auth.common.exception.domain.NotFoundOAuthTokenDecoderException
import com.asterlsker.auth.config.OAuthTokenDecoderConfig
import com.asterlsker.auth.domain.authorization.token.OAuthTokenDecoder
import com.asterlsker.auth.domain.model.Provider
import java.util.*

object OAuthTokenDecoderFactory {

    fun of(beans: Map<String, OAuthTokenDecoder>, provider: Provider): OAuthTokenDecoder {
        val beanName = "${provider.name.lowercase(Locale.getDefault())}${OAuthTokenDecoderConfig.BEAN_NAME_SUFFIX}"
        val decoder = when (provider) {
            Provider.GOOGLE -> beans[beanName]
            Provider.APPLE -> beans[beanName]
            Provider.NAVER -> beans[beanName]
            Provider.KAKAO -> beans[beanName]
        }
        return decoder ?: throw NotFoundOAuthTokenDecoderException()
    }
}