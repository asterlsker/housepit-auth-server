package com.asterlsker.auth.infrastructure.member.entity

import com.asterlsker.auth.domain.member.Member
import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.domain.model.Phone
import com.asterlsker.auth.domain.model.Provider
import com.asterlsker.auth.domain.model.Role
import com.asterlsker.auth.infrastructure.BaseEntity
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "member")
class MemberEntity(
    @Id
    val id: String? = null,

    val userName: String,

    val phone: Phone,

    @Transient
    val memberRoles: List<MemberRoleEntity> = listOf(),

    @Transient
    val memberSocialLogins: List<MemberSocialLoginEntity> = listOf(),
): BaseEntity() {

    fun toDomain(): Member {
        return Member(
            id = this.id,
            userName = this.userName,
            phone = this.phone,
            memberRoles = this.memberRoles.map { it.toDomain() }.toMutableList(),
            memberSocialLogins = this.memberSocialLogins.map { it.toDomain() }.toMutableList()
        )
    }
}