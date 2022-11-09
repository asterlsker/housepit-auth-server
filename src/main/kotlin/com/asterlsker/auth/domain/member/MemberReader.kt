package com.asterlsker.auth.domain.member

import com.asterlsker.auth.domain.model.Email

interface MemberReader {

    suspend fun existsByEmail(email: Email): Boolean

    suspend fun findByEmail(email: Email): Member?
}