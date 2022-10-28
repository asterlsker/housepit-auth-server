package com.asterlsker.auth.common.support

fun Long.toMilliSec(): Long {
    return this * 60 * 1000
}