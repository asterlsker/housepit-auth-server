package com.asterlsker.auth.domain.member

import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.domain.model.Provider
import com.asterlsker.auth.infrastructure.member.entity.MemberSocialLoginEntity

class MemberSocialLogin(
    val id: Long? = null,

    val provider: Provider,

    val email: Email,
) {
    fun toEntity(): MemberSocialLoginEntity {
        return MemberSocialLoginEntity(
            id = this.id,
            provider = this.provider,
            email = this.email
        )
    }
}