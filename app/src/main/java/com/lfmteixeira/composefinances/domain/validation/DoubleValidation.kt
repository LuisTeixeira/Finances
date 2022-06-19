package com.lfmteixeira.composefinances.domain.validation

fun Validator.isNotNegative(fieldName: String, value: Double): Validator {
    if (value < 0) this.invalidate("Field $fieldName must be larger than 0")
    return this
}