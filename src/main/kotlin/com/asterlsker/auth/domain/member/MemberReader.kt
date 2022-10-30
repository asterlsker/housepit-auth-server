package com.asterlsker.auth.domain.member

import com.asterlsker.auth.domain.model.Email

interface MemberReader {

    fun existsByEmail(email: Email): Boolean

    fun findByEmail(email: Email): Member?
}