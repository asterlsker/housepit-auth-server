package com.asterlsker.auth.common.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.commons.lang3.StringUtils
import org.springframework.validation.Errors
import java.util.*
import java.util.stream.Collectors

class ErrorField {
    var field: String? = null
    var value: String? = null
    var reason: String? = null

    companion object {
        fun of(errors: Errors?): List<ErrorField> {
            return if (errors == null) {
                Collections.emptyList()
            } else errors.fieldErrors
                .stream()
                .map { fieldError ->
                    ErrorField().apply {
                        field = fieldError.field
                        value = fieldError.rejectedValue?.let { getOrEmpty(it) }
                        value = fieldError.defaultMessage?.let { getOrEmpty(it) }
                    }
                }
                .collect(Collectors.toList())
        }

        private fun getOrEmpty(target: Any): String {
            return if (Objects.isNull(target)) {
                StringUtils.EMPTY
            } else target.toString()
        }
    }
}