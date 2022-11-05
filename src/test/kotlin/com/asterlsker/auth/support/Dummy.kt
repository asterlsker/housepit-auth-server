package com.asterlsker.auth.support

import com.asterlsker.auth.domain.authorization.AuthCommand
import com.asterlsker.auth.domain.authorization.token.TokenResponse
import com.asterlsker.auth.domain.model.Provider

class MemberDummy {

    data class GoogleUser(
        val oAuthToken: String = "OAuth Token",
        val provider: Provider = Provider.GOOGLE,
        val userName: String = "jungho",
        val email: String = "designjava@gmail.com",
        val phone: String = "01089241810"
    ) {
        fun signInRequest() = AuthCommand.SignInRequest(oAuthToken = this.oAuthToken, provider = this.provider)
        fun tokenResponse() = TokenResponse("ak", "rk")
    }
}