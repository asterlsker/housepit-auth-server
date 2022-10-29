package com.asterlsker.auth.domain.model

import com.asterlsker.auth.common.exception.domain.InvalidEmailException

class Email(
    val email: String
) {

    companion object {
        const val EMAIL_PATTERN = "^[a-zA-Z0-9_!#\$%&'\\*+/=?{|}~^.-]+@[a-zA-Z0-9.-]+\$"
    }

    init {
        if (email.matches(Regex(EMAIL_PATTERN))) throw InvalidEmailException()
    }
}