package com.asterlsker.auth.infrastructure.authorization

import com.asterlsker.auth.common.exception.domain.GoogleOAuthPayloadException
import com.asterlsker.auth.common.exception.domain.InvalidGoogleOAuthIdTokenException
import com.asterlsker.auth.common.exception.domain.UnVerifiedGoogleOAuthEmailException
import com.asterlsker.auth.common.properties.GoogleOAuthProperties
import com.asterlsker.auth.domain.authentication.UserDetails
import com.asterlsker.auth.domain.authorization.token.OAuthTokenDecoder
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import org.apache.commons.lang3.exception.ExceptionUtils
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component("google${OAuthTokenDecoder.BEAN_NAME_SUFFIX}")
class GoogleOAuthTokenDecoder(
    private val googleOAuthProperties: GoogleOAuthProperties,
): OAuthTokenDecoder {

    private val log = LoggerFactory.getLogger(javaClass)

    override suspend fun decode(token: String): UserDetails {
        val verifier: GoogleIdTokenVerifier = GoogleIdTokenVerifier.Builder(NetHttpTransport(), GsonFactory())
            .setAudience(Collections.singletonList(googleOAuthProperties.clientId))
            .build()

        val verifiedToken = try {
            verifier.verify(token)
        } catch (e: Exception) {
            log.error("# [GoogleOAuthTokenDecoder] IdToken Decode Error: ${ExceptionUtils.getMessage(e)}")
            throw InvalidGoogleOAuthIdTokenException()
        }

        val payload = verifiedToken.payload
        if (!payload.emailVerified) throw UnVerifiedGoogleOAuthEmailException()
        val email = payload.email ?: throw GoogleOAuthPayloadException()
        val name = payload["name"] as String?

        return UserDetails(name = name, email = email)
    }
}