package com.asterlsker.auth.infrastructure.member.entity

import com.asterlsker.auth.domain.member.Member
import com.asterlsker.auth.domain.member.MemberRole
import com.asterlsker.auth.domain.member.MemberSocialLogin
import com.asterlsker.auth.domain.model.Phone
import com.asterlsker.auth.infrastructure.BaseEntity
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "member")
class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "member_uuid")
    val memberUuid: String,

    @Column(name = "user_name")
    val userName: String,

    @Column(name = "phone")
    val phone: String? = null,

    @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL])
    val memberRoles: MutableList<MemberRoleEntity> = mutableListOf(),

    @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL])
    val memberSocialLogins: MutableList<MemberSocialLoginEntity> = mutableListOf(),
): BaseEntity() {

    companion object {
        fun of(member: Member): MemberEntity {
            val entity = MemberEntity(
                id = member.id?.toLong(),
                memberUuid = member.memberUuid,
                userName = member.getUserName(),
                phone = member.phone?.value,
            )

            entity.addRoles(*member.memberRoles.toTypedArray())
            entity.addSocialLogins(*member.memberSocialLogins.toTypedArray())

            return entity
        }
    }

    fun addRoles(vararg memberRoles: MemberRole) {
        memberRoles.forEach {
            val entity = MemberRoleEntity.of(it)
            this.memberRoles.add(entity)
            entity.member = this
        }
    }

    fun addSocialLogins(vararg memberSocialLogins: MemberSocialLogin) {
        memberSocialLogins.forEach {
            val entity = MemberSocialLoginEntity.of(it)
            this.memberSocialLogins.add(entity)
            entity.member = this
        }
    }

    fun toDomain(): Member {
        return Member(
            id = this.id.toString(),
            memberUuid = this.memberUuid,
            userName = this.userName,
            phone = this.phone?.let { Phone(it) },
            memberRoles = this.memberRoles.map { it.toDomain() }.toMutableList(),
            memberSocialLogins = this.memberSocialLogins.map { it.toDomain() }.toMutableList()
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MemberEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}