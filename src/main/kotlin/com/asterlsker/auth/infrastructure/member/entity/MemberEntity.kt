package com.asterlsker.auth.infrastructure.member.entity

import com.asterlsker.auth.domain.member.Member
import com.asterlsker.auth.domain.member.MemberRole
import com.asterlsker.auth.domain.member.MemberSocialLogin
import com.asterlsker.auth.domain.model.Phone
import com.asterlsker.auth.infrastructure.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "member")
class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String? = null,

    @Column(name = "user_name")
    val userName: String,

    @Column(name = "phone")
    val phone: String,

    @OneToMany(mappedBy = "member")
    val memberRoles: MutableList<MemberRoleEntity> = mutableListOf(),

    @OneToMany(mappedBy = "member")
    val memberSocialLogins: MutableList<MemberSocialLoginEntity> = mutableListOf(),
): BaseEntity() {

    companion object {
        fun of(member: Member): MemberEntity {
            val entity = MemberEntity(
                id = member.id,
                userName = member.userName,
                phone = member.phone.value,
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
            id = this.id,
            userName = this.userName,
            phone = Phone(this.phone),
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