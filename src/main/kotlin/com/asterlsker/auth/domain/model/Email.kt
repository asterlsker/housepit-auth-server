package com.asterlsker.auth.domain.model

import com.asterlsker.auth.common.exception.domain.InvalidEmailException

class Email(
    val value: String
) {

    val id: String
    val domain: String

    companion object {
        const val EMAIL_PATTERN = "^[a-zA-Z0-9.-]+@[a-zA-Z0-9.-]+.[a-zA-Z0-9._-]+\$"
    }

    init {
        if (!value.matches(Regex(EMAIL_PATTERN))) throw InvalidEmailException()
        val properties = value.split("@")
        id = properties[0]
        domain = properties[1]
    }
}