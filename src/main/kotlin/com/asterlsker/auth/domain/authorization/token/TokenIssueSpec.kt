package com.asterlsker.auth.domain.authorization.token

import com.asterlsker.auth.domain.model.Provider

data class TokenIssueSpec(
    val email: String,
    val provider: Provider
)