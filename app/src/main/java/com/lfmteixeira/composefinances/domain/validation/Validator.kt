package com.lfmteixeira.composefinances.domain.validation

import com.lfmteixeira.composefinances.domain.exception.ValidationException

class Validator {
    private var validated = true
    private var messageString = ""
    val detailsMap: MutableMap<String, String> = HashMap()


    fun invalidate(property: String, message: String) {
        validated = false
        messageString += "\n" + message
        detailsMap[property] = message
    }

    fun isValidated(): Boolean {
        return validated
    }

    fun getMessage(): String {
        return messageString
    }
}

fun validate(validations: (Validator) -> Validator) {
    val validateStatus = validations(Validator())
    if (!validateStatus.isValidated()) {
        throw ValidationException(validateStatus.getMessage(), validateStatus.detailsMap)
    }
}