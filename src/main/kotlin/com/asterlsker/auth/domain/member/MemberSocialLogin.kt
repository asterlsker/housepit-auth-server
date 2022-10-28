package com.asterlsker.auth.domain.member

import com.asterlsker.auth.domain.BaseEntity
import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.domain.model.Provider
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "member_social_login")
@Entity
class MemberSocialLogin(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(value = EnumType.STRING)
    val provider: Provider,

    @Embedded
    val email: Email,

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne
    val member: Member
): BaseEntity()