package com.lfmteixeira.composefinances.domain.validation

import java.time.LocalDate

fun Validator.isNotInTheFuture(fieldName: String, value: LocalDate): Validator {
    if (value > LocalDate.now()) this.invalidate("Field $fieldName must not be in the future")
    return this
}