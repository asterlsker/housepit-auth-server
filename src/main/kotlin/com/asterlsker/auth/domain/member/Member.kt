package com.asterlsker.auth.domain.member

import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.domain.model.Phone
import com.asterlsker.auth.domain.model.Provider
import com.asterlsker.auth.domain.model.Role
import com.asterlsker.auth.infrastructure.member.entity.MemberEntity

class Member(
    val id: String? = null,

    val userName: String,

    val phone: Phone,

    val memberRoles: MutableList<MemberRole> = mutableListOf(),

    val memberSocialLogins: MutableList<MemberSocialLogin> = mutableListOf(),
) {
    fun link(provider: Provider, email: Email) {
        this.memberRoles.add(MemberRole(role = Role.USER))
        this.memberSocialLogins.add(MemberSocialLogin(provider = provider, email = email))
    }

    fun toEntity(): MemberEntity {
        return MemberEntity(
            id = this.id,
            userName = this.userName,
            phone = this.phone,
            memberRoles = this.memberRoles.map { it.toEntity() },
            memberSocialLogins = this.memberSocialLogins.map { it.toEntity() }
        )
    }
}