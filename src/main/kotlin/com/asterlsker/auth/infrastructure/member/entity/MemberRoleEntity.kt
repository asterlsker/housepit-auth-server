package com.asterlsker.auth.infrastructure.member.entity

import com.asterlsker.auth.domain.member.MemberRole
import com.asterlsker.auth.domain.model.Role
import com.asterlsker.auth.infrastructure.BaseEntity
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "member_role")
class MemberRoleEntity(
    @Id
    val id: Long? = null,

    val role: Role,
): BaseEntity() {

    fun toDomain(): MemberRole {
        return MemberRole(
            id = this.id,
            role = this.role
        )
    }
}