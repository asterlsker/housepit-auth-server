package com.asterlsker.auth.domain.authorization

data class TokenIssueSpec(
    val payload: String,
    val provider: Provider
)