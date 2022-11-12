package com.asterlsker.auth.infrastructure.authorization

import com.asterlsker.auth.common.exception.domain.InvalidGoogleOAuthIdTokenException
import com.asterlsker.auth.common.properties.GoogleOAuthProperties
import com.asterlsker.auth.domain.authentication.UserDetails
import com.asterlsker.auth.domain.authorization.token.OAuthTokenDecoder
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import org.springframework.stereotype.Component
import java.util.*

@Component("google${OAuthTokenDecoder.BEAN_NAME_SUFFIX}")
class GoogleOAuthTokenDecoder(
    private val googleOAuthProperties: GoogleOAuthProperties,
): OAuthTokenDecoder {
    override suspend fun decode(token: String): UserDetails {
        val verifier: GoogleIdTokenVerifier = GoogleIdTokenVerifier.Builder(NetHttpTransport(), GsonFactory())
            .setAudience(Collections.singletonList(googleOAuthProperties.clientId))
            .build()

        val verifiedToken = verifier.verify(token) ?: throw InvalidGoogleOAuthIdTokenException()
        val payload = verifiedToken.payload
        val email = payload.email
        val name = payload["name"] as String

        return UserDetails(name = name, email = email)
    }
}