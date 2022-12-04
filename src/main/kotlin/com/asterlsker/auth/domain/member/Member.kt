package com.asterlsker.auth.domain.member

import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.domain.model.Phone
import com.asterlsker.auth.domain.model.Provider
import com.asterlsker.auth.domain.model.Role
import org.apache.commons.lang3.RandomStringUtils
import java.util.UUID

data class Member(
    val id: String? = null,

    val memberUuid: String,

    private var userName: String,

    private var phone: Phone? = null,

    private var ci: String? = null,

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

    fun update(userName: String) {
        this.userName = userName
    }

    fun saveCertification(phone: Phone, ci: String) {
        this.phone = phone
        this.ci = ci
    }

    fun getRoles() = memberRoles.map { it.role }

    fun getUserName() = this.userName
    fun getPhone() = this.phone
    fun getCi() = this.ci

    companion object {
        fun new(email: Email) = Member(userName = createUserName(email), memberUuid = createUuid())

        private fun createUserName(email: Email): String {
            val number = RandomStringUtils.random(4, false, true)
            return "${email.id}#${number}"
        }

        private fun createUuid() = UUID.randomUUID().toString()
    }
}