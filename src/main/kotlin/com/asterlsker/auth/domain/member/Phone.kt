package com.asterlsker.auth.domain.member

import com.asterlsker.auth.common.exception.domain.InvalidPhoneException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import com.google.type.PhoneNumber
import org.slf4j.LoggerFactory
import javax.persistence.Embeddable

@Embeddable
class Phone(
    val phone: String
) {
    private val phoneNumber: Phonenumber.PhoneNumber
    private val phoneNumberUtil = PhoneNumberUtil.getInstance()

    init {
        val phoneNumber = try {
            phoneNumberUtil.parse(phone, "KR")
        } catch (e: Exception) {
            throw InvalidPhoneException()
        }
        this.phoneNumber = phoneNumber
    }
}