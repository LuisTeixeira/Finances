package com.lfmteixeira.composefinances.domain.validation

class Validator {
    private var validated = true
    private var messageString = ""

    fun invalidate(message: String) {
        validated = false
        messageString += "\n" + message
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
        throw IllegalArgumentException(validateStatus.getMessage())
    }
}