package com.lfmteixeira.composefinances.domain.validation

fun Validator.isPresent(fieldName: String, value: String): Validator {
    if (value.isNullOrBlank()) this.invalidate(fieldName, "Field $fieldName must not be empty")
    return this
}