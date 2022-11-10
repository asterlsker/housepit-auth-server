package com.asterlsker.auth.domain.member

import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.domain.model.Phone
import com.asterlsker.auth.domain.model.Provider
import com.asterlsker.auth.domain.model.Role

data class Member(
    val id: String? = null,

    val userName: String,

    val phone: Phone,

    val memberRoles: MutableList<MemberRole> = mutableListOf(),

    val memberSocialLogins: MutableList<MemberSocialLogin> = mutableListOf(),
) {
    fun register(memberSocialLogin: MemberSocialLogin) {
        this.memberSocialLogins.add(memberSocialLogin)
        this.memberRoles.add(MemberRole(role = Role.USER))
    }

    fun link(provider: Provider, email: Email) {
        this.memberRoles.add(MemberRole(role = Role.USER))
        this.memberSocialLogins.add(MemberSocialLogin(provider = provider, email = email))
    }
}