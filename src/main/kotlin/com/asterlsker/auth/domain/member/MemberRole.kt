package com.asterlsker.auth.domain.member

import com.asterlsker.auth.infrastructure.BaseEntity
import com.asterlsker.auth.domain.model.Role
import com.asterlsker.auth.infrastructure.member.entity.MemberRoleEntity

class MemberRole(
    val id: Long? = null,

    val role: Role,
) {
    fun toEntity(): MemberRoleEntity {
        return MemberRoleEntity(
            id = this.id,
            role = this.role
        )
    }
}