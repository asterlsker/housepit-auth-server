package com.asterlsker.auth.domain.authorization

import com.asterlsker.auth.common.exception.domain.InvalidTokenException
import com.asterlsker.auth.common.exception.domain.NotExistMemberException
import com.asterlsker.auth.domain.member.MemberReader
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwtDecoder: JwtDecoder,
    private val jwtProvider: JwtProvider,
    private val memberReader: MemberReader
) {

    fun signIn(request: AuthCommand.SignInRequest): AuthCommand.SignInResponse {
        val email = jwtDecoder.decodeBase64(request.token)
        validateMember(email)
        val tokens = jwtProvider.issueTokens(TokenIssueSpec(payload = email, provider = request.provider))

        return AuthCommand.SignInResponse(accessToken = tokens.accessToken, refreshToken = tokens.refreshToken)
    }

    fun signOut(request: AuthCommand.SignOutRequest) {
        when (jwtProvider.validateToken(request.accessToken)) {
            true -> jwtProvider.releaseTokens(request.accessToken)
            false -> throw InvalidTokenException()
        }
    }

    private fun validateMember(email: String) {
        when (memberReader.existsByEmail(email)) {
            true -> return
            false -> throw NotExistMemberException()
        }
    }
}