package com.asterlsker.auth.domain.authorization.token

import com.asterlsker.auth.domain.model.Provider
import com.asterlsker.auth.domain.model.Role

data class TokenIssueSpec(
    val memberUuid: String,
    val memberRoles: List<Role>,
    val email: String,
    val provider: Provider
)