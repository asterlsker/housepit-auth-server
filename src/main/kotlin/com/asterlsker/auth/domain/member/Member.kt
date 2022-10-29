package com.asterlsker.auth.domain.member

import com.asterlsker.auth.domain.BaseEntity
import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.domain.model.Phone
import com.asterlsker.auth.domain.model.Provider
import com.asterlsker.auth.domain.model.Role
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
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

    @OneToMany
    @JoinColumn(name = "member_id")
    val memberRoles: MutableList<MemberRole>,

    @OneToMany
    @JoinColumn(name = "member_id")
    val memberSocialLogins: MutableList<MemberSocialLogin>,
): BaseEntity() {
    fun link(provider: Provider, email: String) {
        this.memberRoles.add(MemberRole(role = Role.USER))
        this.memberSocialLogins.add(MemberSocialLogin(provider = provider, email = Email(email)))
    }
}