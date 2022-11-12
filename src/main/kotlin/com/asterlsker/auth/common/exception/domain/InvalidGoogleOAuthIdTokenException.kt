package com.asterlsker.auth.common.exception.domain

import com.asterlsker.auth.common.exception.DomainException
import com.asterlsker.auth.common.response.ErrorCode

class InvalidGoogleOAuthIdTokenException(
    override val errorCode: ErrorCode = ErrorCode.INVALID_GOOGLE_OAUTH_TOKEN
): DomainException(errorCode)