package com.asterlsker.auth.common.exception.domain

import com.asterlsker.auth.common.exception.DomainException
import com.asterlsker.auth.common.response.ErrorCode

class GoogleOAuthPayloadException(
    override val errorCode: ErrorCode = ErrorCode.MISSING_REQUIRED_PROPERTY_IN_GOOGLE_OAUTH_PAYLOAD
): DomainException()