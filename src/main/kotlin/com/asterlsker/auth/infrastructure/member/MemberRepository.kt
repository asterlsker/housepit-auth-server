package com.asterlsker.auth.infrastructure.member

import com.asterlsker.auth.domain.member.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MemberRepository: JpaRepository<Member, String> {

    // TODO JPQL exists 우회 방식 = 성능 이슈 있음 (https://jojoldu.tistory.com/516)
    @Query("select count(m) > 0 from Member m join fetch m.memberSocialLogins ms where ms.email = :email")
    fun existsByEmail(@Param("email") email: String): Boolean

    @Query("select m from Member m join fetch m.memberRoles mr join fetch m.memberSocialLogins ms where ms.email = :email")
    fun findByEmail(@Param("email") email: String): Member
}