package com.asterlsker.auth.common.exception

import com.asterlsker.auth.common.response.ErrorCode
import java.lang.RuntimeException

class EntityException(
   val errorCode: ErrorCode = ErrorCode.ENTITY
): RuntimeException(errorCode.message)