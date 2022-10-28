package com.asterlsker.auth.domain.model

import com.asterlsker.auth.common.exception.domain.NotFoundProviderException

enum class Provider {

    GOOGLE, APPLE, KAKAO, NAVER,
    ;

    companion object {
        fun findByName(name: String): Provider {
            return Provider.values().find { it.name == name }
                ?: throw NotFoundProviderException()
        }
    }
}