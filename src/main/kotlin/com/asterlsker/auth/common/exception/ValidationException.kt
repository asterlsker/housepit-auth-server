package com.asterlsker.auth.common.exception

import com.asterlsker.auth.common.response.ErrorCode
import org.springframework.validation.Errors
import java.lang.RuntimeException

class ValidationException(
    val errorCode: ErrorCode = ErrorCode.VALIDATION,
    val errors: Errors? = null
): RuntimeException(errorCode.message)
