package com.asterlsker.auth.domain.authorization

data class TokenIssueSpec(
    val email: String,
    val provider: Provider
)