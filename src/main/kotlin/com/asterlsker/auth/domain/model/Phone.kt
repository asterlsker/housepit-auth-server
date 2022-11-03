package com.asterlsker.auth.domain.model

import com.asterlsker.auth.common.exception.domain.InvalidPhoneException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber

class Phone(
    val phone: String
) {
//    private val phoneNumber: Phonenumber.PhoneNumber
//    private val phoneNumberUtil = PhoneNumberUtil.getInstance()
//
//    init {
//        val phoneNumber = try {
//            phoneNumberUtil.parse(phone, "KR")
//        } catch (e: Exception) {
//            throw InvalidPhoneException()
//        }
//        this.phoneNumber = phoneNumber
//    }
}