package com.asterlsker.auth.infrastructure.member.entity

import com.asterlsker.auth.domain.member.MemberRole
import com.asterlsker.auth.domain.model.Role
import com.asterlsker.auth.infrastructure.BaseEntity
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "member_role")
class MemberRoleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(value = EnumType.STRING)
    val role: Role,
): BaseEntity() {

    @JoinColumn(name = "member_id")
    @ManyToOne(targetEntity = MemberEntity::class, optional = false)
    internal lateinit var member: MemberEntity

    companion object {
        fun of(memberRole: MemberRole): MemberRoleEntity {
            return MemberRoleEntity(
                id = memberRole.id,
                role = memberRole.role
            )
        }
    }

    fun toDomain(): MemberRole {
        return MemberRole(
            id = this.id,
            role = this.role
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MemberRoleEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}