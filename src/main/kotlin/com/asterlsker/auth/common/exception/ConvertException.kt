package com.asterlsker.auth.common.exception

import com.asterlsker.auth.common.response.ErrorCode
import java.lang.RuntimeException

class ConvertException(
   val errorCode: ErrorCode = ErrorCode.CONVERT
): RuntimeException(errorCode.message)