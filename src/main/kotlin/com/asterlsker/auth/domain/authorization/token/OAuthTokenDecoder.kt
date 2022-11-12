package com.asterlsker.auth.domain.authorization.token

import com.asterlsker.auth.domain.authentication.UserDetails

interface OAuthTokenDecoder {

    companion object BeanSpecification {
        const val BEAN_NAME_SUFFIX = "OAuthTokenDecoder"
        const val GOOGLE = "google"
        const val APPLE = "apple"
        const val NAVER = "naver"
        const val KAKAO = "kakao"
    }

    suspend fun decode(token: String): UserDetails
}