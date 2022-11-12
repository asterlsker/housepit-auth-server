package com.asterlsker.auth.domain.member

import com.asterlsker.auth.domain.model.Role

data class MemberRole(
    val id: Long? = null,

    val role: Role,
)