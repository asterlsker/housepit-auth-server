package com.asterlsker.auth.infrastructure.member

import com.asterlsker.auth.domain.member.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, String> {
    fun existsByEmail(email: String): Boolean
}