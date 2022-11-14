package com.asterlsker.auth.domain.member

import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.domain.model.Phone
import com.asterlsker.auth.domain.model.Provider
import com.asterlsker.auth.domain.model.Role
import org.apache.commons.lang3.RandomStringUtils

// TODO client 에서 사용할 unique 한 UUID 만들기
data class Member(
    val id: String? = null,

    val userName: String? = null,

    val phone: Phone? = null,

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

    companion object {
        fun new(email: Email) = Member(userName = createUserName(email))
        private fun createUserName(email: Email): String {
            val number = RandomStringUtils.random(4, false, true)
            return "${email.id}#${number}"
        }
    }
}