package com.asterlsker.auth.domain.model

import javax.persistence.Embeddable

@Embeddable
class Email(
    val email: String
)