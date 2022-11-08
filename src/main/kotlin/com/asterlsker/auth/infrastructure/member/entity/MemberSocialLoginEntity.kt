package com.asterlsker.auth.infrastructure.member.entity

import com.asterlsker.auth.domain.member.MemberSocialLogin
import com.asterlsker.auth.infrastructure.BaseEntity
import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.domain.model.Provider
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "member_social_login")
class MemberSocialLoginEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(value = EnumType.STRING)
    val provider: Provider,

    val email: String,
): BaseEntity() {

    @JoinColumn(name = "member_id")
    @ManyToOne(targetEntity = MemberEntity::class, optional = false)
    internal lateinit var member: MemberEntity

    companion object {
        fun of(memberSocialLogin: MemberSocialLogin): MemberSocialLoginEntity {
            return MemberSocialLoginEntity(
                id = memberSocialLogin.id,
                provider = memberSocialLogin.provider,
                email = memberSocialLogin.email.value
            )
        }
    }

    fun toDomain(): MemberSocialLogin {
        return MemberSocialLogin(
            id = this.id,
            provider = this.provider,
            email = Email(this.email)
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MemberSocialLoginEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}