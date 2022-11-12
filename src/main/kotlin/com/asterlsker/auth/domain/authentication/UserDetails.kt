package com.asterlsker.auth.domain.authentication

data class UserDetails(
    val name: String,
    val email: String,
    val phone: String? = null
)