package com.asterlsker.auth.domain.member

import com.asterlsker.auth.domain.BaseEntity
import com.asterlsker.auth.domain.model.Phone
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Table(name = "member")
@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String? = null,

    @Column
    val userName: String,

    @Embedded
    @Column
    val phone: Phone,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    val memberRole: MemberRole,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    val memberSocialLogin: MemberSocialLogin,
): BaseEntity()