package com.asterlsker.auth.domain.member

import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.domain.model.Provider
import com.asterlsker.auth.infrastructure.member.entity.MemberSocialLoginEntity

data class MemberSocialLogin(
    val id: Long? = null,

    val provider: Provider,

    val email: Email,
)