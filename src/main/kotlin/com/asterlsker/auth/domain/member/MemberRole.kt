package com.asterlsker.auth.domain.member

import com.asterlsker.auth.domain.BaseEntity
import com.asterlsker.auth.domain.model.Role
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "member_role")
@Entity
class MemberRole(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    val role: Role,

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne
    val member: Member
): BaseEntity()