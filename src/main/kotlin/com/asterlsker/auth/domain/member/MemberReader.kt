package com.asterlsker.auth.domain.member

interface MemberReader {

    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): Member
}