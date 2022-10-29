package com.asterlsker.auth.infrastructure.member.entity

import com.asterlsker.auth.domain.member.MemberSocialLogin
import com.asterlsker.auth.infrastructure.BaseEntity
import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.domain.model.Provider
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "member_social_login")
class MemberSocialLoginEntity(
    @Id
    val id: Long? = null,

    val provider: Provider,

    val email: Email,
): BaseEntity() {

    fun toDomain(): MemberSocialLogin {
        return MemberSocialLogin(
            id = this.id,
            provider = this.provider,
            email = this.email
        )
    }
}